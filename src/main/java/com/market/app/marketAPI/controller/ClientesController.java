package com.market.app.marketAPI.controller;

import com.market.app.marketAPI.dto.ClienteRequestDTO;
import com.market.app.marketAPI.dto.GeneralResponseDTO;
import com.market.app.marketAPI.entity.Clientes;
import com.market.app.marketAPI.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.market.app.marketAPI.utils.Constants.ErrorMessages.*;
import static com.market.app.marketAPI.utils.Constants.SucessfullMessages.*;

@CrossOrigin
@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping("/")
    public ResponseEntity<List<Clientes>> getAllClients() {
        return ResponseEntity.ok().body(clientesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clientes>> getClientById(@PathVariable BigInteger id) {
        return ResponseEntity.ok().body(clientesService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponseDTO> createClient(@RequestBody @Valid ClienteRequestDTO client) {
        try {
            if (client.getCedula_cliente() == null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage("La cédula del cliente es obligatoria");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            Clientes clientExist = clientesService.getById(client.getCedula_cliente()).orElse(null);
            if (clientExist != null) {
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage(MESSAGE_RESOURCE_CONFLICT);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            clientesService.create(client);
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_RESOURCE_CREATED);
            return ResponseEntity.ok().body(response);
        } catch (Exception exception) {
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_INTERNAL_SERVER_ERROR + exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponseDTO> updateClientById(@PathVariable BigInteger id, @RequestBody @Valid ClienteRequestDTO client) {
        try {
            Clientes updateClient = clientesService.update(client.toEntity(), id);
            if (updateClient == null) {
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
    public ResponseEntity<GeneralResponseDTO> deleteClientById(@PathVariable BigInteger id) {
        Clientes clientExist = clientesService.getById(id).orElse(null);
        if (clientExist == null) {
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_RESOURCE_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        try{
            clientesService.delete(id);
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_RESOURCE_DELETED);
            return ResponseEntity.ok().body(response);
        }catch (Exception exception){
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_INTERNAL_SERVER_ERROR + exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
