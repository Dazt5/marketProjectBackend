package com.market.app.marketAPI.services;

import com.market.app.marketAPI.dto.GeneralResponseDTO;
import com.market.app.marketAPI.dto.ProductosVendidosDTO;
import com.market.app.marketAPI.dto.VentaRequestDTO;
import com.market.app.marketAPI.entity.*;
import com.market.app.marketAPI.repository.interfaces.*;
import com.market.app.marketAPI.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;

@Service
public class VentasService {

    @Autowired
    private JWTManager jwtManager;

    @Autowired
    private IUsuariosRepository usuariosRepository;

    @Autowired
    private IClientesRepository clientesRepository;

    @Autowired
    private IVentasRepository ventasRepository;

    @Autowired
    private IProductosRepository productosRepository;

    @Autowired
    private IDetalleVentasRepository detalleVentasRepository;

    public ResponseEntity<GeneralResponseDTO> realizarVenta(VentaRequestDTO venta, HttpServletRequest request) {
        GeneralResponseDTO response = new GeneralResponseDTO();

        //Desencriptar el token y obtener el usuario del administrado que hizo la venta.
        String username = jwtManager.getUsernameInToken(request);

        //Buscar al usuario por su nombre de usuario y verificar si es válido.
        Usuarios usuario = usuariosRepository.findByUsuario(username);
        if (usuario == null) {
            response.setMessage("El usuario al que hace referencia la sesión no está disponible, " +
                    "cierre sesión e intente de nuevo");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        //Buscar al cliente por su cédula y verificar si es válido.
        Clientes cliente = clientesRepository.findById(venta.getCedulaCliente()).orElse(null);
        if(cliente == null){
            response.setMessage("El cliente al que hace referencia no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        //Calculo del total de la venta.
        double valorTotalCompra = 0, subTotalCompra = 0, totalIvaCompra = 0;
        ArrayList<ProductosVendidosDTO> productos = venta.getProductos();
        for (ProductosVendidosDTO p : productos) {
            Productos producto = productosRepository.findById(p.getCodigoProducto()).orElse(null);
            if (producto == null) {
                response.setMessage("Un producto al que hace referencia no está disponible.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            subTotalCompra += p.getCantidadProducto() * producto.getPrecio_venta();
            totalIvaCompra += (p.getCantidadProducto() * producto.getPrecio_venta()) * (producto.getIvacompra() / 100);
        }
        valorTotalCompra = subTotalCompra + totalIvaCompra;

        /*Verificar si el cálculo del frontend es correcto, sino, enviar una alerta.*/
        if (venta.getSubTotal() != subTotalCompra || venta.getTotalIva() != totalIvaCompra || venta.getTotalVenta() != valorTotalCompra) {
            System.out.println("WARNING: EL CALCULO REALIZADO EN EL FRONTEND NO ES PRECISO, REVISAR");
            response.setMessage(" Calculo realizado por el frontend no es preciso");
        }
        //Realizar la venta.
        Ventas nuevaVenta = new Ventas();
        nuevaVenta.setCedulaCliente(venta.getCedulaCliente());
        nuevaVenta.setCedulaUsuario(usuario.getCedulaUsuario());
        nuevaVenta.setIvaVenta(totalIvaCompra);
        nuevaVenta.setValorVenta(subTotalCompra);
        nuevaVenta.setTotalVenta(valorTotalCompra);
        Ventas ventaRealizada = ventasRepository.save(nuevaVenta);

        //Ingresar en la base de datos el detalle de la venta.
        for (ProductosVendidosDTO p : productos) {
            Productos producto = productosRepository.findById(p.getCodigoProducto()).orElse(null);
            if (producto == null) {
                response.setMessage("Un producto al que hace referencia no está disponible.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            DetalleVentas detalleVenta = new DetalleVentas();
            detalleVenta.setCantidadProducto(p.getCantidadProducto());
            detalleVenta.setCodigoProducto(producto.getCodigo_producto());
            detalleVenta.setCodigoVenta(ventaRealizada.getCodigo_venta());
            detalleVenta.setValorVenta(producto.getPrecio_venta() * p.getCantidadProducto());
            detalleVenta.setValorIva((producto.getPrecio_venta() * p.getCantidadProducto()) * (producto.getIvacompra()/100));
            detalleVenta.setValorTotal(detalleVenta.getValorVenta() + detalleVenta.getValorIva());
            detalleVentasRepository.save(detalleVenta);
        }

        response.setMessage("La compra ha sido realizada exitosamente"+ response.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ArrayList<Ventas> getVentas(){

        return (ArrayList<Ventas>) ventasRepository.findAll();
    }

    public ResponseEntity<?> getVentaByCodigo(BigInteger codigoVenta){
        Ventas venta = ventasRepository.findById(codigoVenta).orElse(null);

        if(venta == null){
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage("La venta a la que hace referencia no existe.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok().body(venta);
    }

    public ArrayList<Ventas> getVentaByCedulaCliente(BigInteger cedulaCliente){
        return ventasRepository.findByCedulaCliente(cedulaCliente);
    }

    public ArrayList<DetalleVentas> getAllDetalleVenta() {
        return (ArrayList<DetalleVentas>) detalleVentasRepository.findAll();
    }

    public ArrayList<DetalleVentas> getDetalleVentaById(BigInteger id){
        return detalleVentasRepository.findByCodigoVenta(id);
    }
}