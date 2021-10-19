package com.market.app.marketAPI.controller;

import com.market.app.marketAPI.dto.VentaRequestDTO;
import com.market.app.marketAPI.services.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;

@CrossOrigin
@RestController
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    private VentasService ventasService;

    @PostMapping("/")
    public ResponseEntity<?> realizarVenta(@RequestBody @Valid VentaRequestDTO venta, HttpServletRequest request){
        return ventasService.realizarVenta(venta,request);
    }

    @GetMapping("/")
    public ResponseEntity<?> getVentas(){
        return ResponseEntity.ok().body(ventasService.getVentas());
    }

    @GetMapping("/venta/{id}")
    public ResponseEntity<?> getVentaByCodigo(@PathVariable BigInteger id){
        return ventasService.getVentaByCodigo(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVentaByCedulaCliente(@PathVariable BigInteger id){
        return ResponseEntity.ok().body(ventasService.getVentaByCedulaCliente(id));
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<?> detallarVenta(@PathVariable BigInteger id){
        return ResponseEntity.ok().body(ventasService.getDetalleVentaById(id));
    }
}
