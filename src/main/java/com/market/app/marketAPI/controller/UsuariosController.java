package com.market.app.marketAPI.controller;

import com.market.app.marketAPI.dto.GeneralResponseDTO;
import com.market.app.marketAPI.dto.UsuarioRequestDTO;
import com.market.app.marketAPI.entity.Usuarios;
import com.market.app.marketAPI.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.market.app.marketAPI.utils.Constants.SucessfullMessages.*;
import static com.market.app.marketAPI.utils.Constants.ErrorMessages.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/")
    public ResponseEntity<List<Usuarios>> getAllUsers() {
        return ResponseEntity.ok().body(usuariosService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuarios>> geUserById(@PathVariable BigInteger id) {
        return ResponseEntity.ok().body(usuariosService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponseDTO> createUser(@RequestBody @Validated UsuarioRequestDTO user) {
        try {

            if (user.getCedulaUsuario() == null){
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage("La cédula del usuario es obligatoria");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Usuarios userExist = usuariosService.getById(user.getCedulaUsuario()).orElse(null);
            if(userExist != null){
                GeneralResponseDTO response = new GeneralResponseDTO();
                response.setMessage(MESSAGE_RESOURCE_CONFLICT);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            usuariosService.create(user);
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
    public ResponseEntity<GeneralResponseDTO> updateUserById(@RequestBody @Validated UsuarioRequestDTO user, @PathVariable BigInteger id) {
        try {

            Usuarios updateUser = usuariosService.update(user.toEntity(user), id);

            if (updateUser == null) {
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
    public ResponseEntity<GeneralResponseDTO> deleteUserById(@PathVariable BigInteger id) {

        Usuarios userExist = usuariosService.getById(id).orElse(null);

        if (userExist == null) {
            GeneralResponseDTO response = new GeneralResponseDTO();
            response.setMessage(MESSAGE_RESOURCE_NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        try {
            usuariosService.delete(id);
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
