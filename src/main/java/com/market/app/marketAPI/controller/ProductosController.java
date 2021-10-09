package com.market.app.marketAPI.controller;

import com.market.app.marketAPI.dto.GeneralResponseDTO;
import com.market.app.marketAPI.dto.ProductosRequestDTO;
import com.market.app.marketAPI.entity.Productos;
import com.market.app.marketAPI.services.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.market.app.marketAPI.utils.Constants.ErrorMessages.*;
import static com.market.app.marketAPI.utils.Constants.SuccessfulMessages.*;

@CrossOrigin
@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductosService productosService;

    @GetMapping("/")
    public ResponseEntity<List<Productos>> getAllProductos() {
        return ResponseEntity.ok().body(productosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Productos>> getProductoById(@PathVariable BigInteger id) {
        return ResponseEntity.ok().body(productosService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponseDTO> createProducto(@RequestBody @Valid ProductosRequestDTO producto) {
        try {

            if (producto.getCodigo_producto() == null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage("El código del producto es obligatorio");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Productos productoExist = productosService.getById(producto.getCodigo_producto()).orElse(null);

            if (productoExist != null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage(MESSAGE_RESOURCE_CONFLICT);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            productosService.create(producto.toEntity());
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_RESOURCE_CREATED);
            return ResponseEntity.ok().body(response);
        } catch (Exception exception) {
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO> updateProductoById(@PathVariable BigInteger id, @RequestBody @Valid ProductosRequestDTO producto) {
        try {
            Productos updateProducto = productosService.update(producto.toEntity(), id);
            if (updateProducto == null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage(MESSAGE_RESOURCE_NOT_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_RESOURCE_UPDATED);
            return ResponseEntity.ok().body(response);
        } catch (Exception exception) {
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_INTERNAL_SERVER_ERROR + exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO> deleteProductoById(@PathVariable BigInteger id) {
        try {
            Productos productoExist = productosService.getById(id).orElse(null);
            if (productoExist == null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage(MESSAGE_RESOURCE_NOT_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            productosService.delete(id);
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_RESOURCE_DELETED);
            return ResponseEntity.ok().body(response);
        } catch (Exception exception) {
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_INTERNAL_SERVER_ERROR + exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}