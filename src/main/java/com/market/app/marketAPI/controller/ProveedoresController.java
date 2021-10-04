package com.market.app.marketAPI.controller;

import com.market.app.marketAPI.dto.GeneralResponseDTO;
import com.market.app.marketAPI.dto.ProveedoresRequestDTO;
import com.market.app.marketAPI.entity.Proveedores;
import com.market.app.marketAPI.services.ProveedoresService;

import static com.market.app.marketAPI.utils.Constants.ErrorMessages.*;
import static com.market.app.marketAPI.utils.Constants.SuccessfulMessages.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedoresService proveedoresService;

    @GetMapping("/")
    public ResponseEntity<List<Proveedores>> getAllProveedores() {
        return ResponseEntity.ok().body(proveedoresService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Proveedores>> getProveedorById(@PathVariable BigInteger id) {
        return ResponseEntity.ok().body(proveedoresService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponseDTO> createProveedor(@RequestBody @Valid ProveedoresRequestDTO proveedor) {
        try {
            if (proveedor.getNitProveedor() == null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage("El Nit del proveedor es obligatoria");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            Proveedores proveedorExist = proveedoresService.getById(proveedor.getNitProveedor()).orElse(null);
            if (proveedorExist != null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage(MESSAGE_RESOURCE_CONFLICT);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            proveedoresService.create(proveedor);
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
    public ResponseEntity<GeneralResponseDTO> updateProveedorById(@PathVariable BigInteger id, @RequestBody @Valid ProveedoresRequestDTO proveedor) {
        try {
            Proveedores updateProveedor = proveedoresService.update(proveedor.toEntity(), id);
            if (updateProveedor == null) {
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
    public ResponseEntity<GeneralResponseDTO> deleteProveedorById(@PathVariable BigInteger id) {
        try {
            Proveedores proveedorExist = proveedoresService.getById(id).orElse(null);
            if (proveedorExist == null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage(MESSAGE_RESOURCE_NOT_FOUND);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            proveedoresService.delete(id);
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
