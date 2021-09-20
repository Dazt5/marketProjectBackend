package com.market.app.marketAPI.services;

import com.market.app.marketAPI.dto.LoginRequestDTO;
import com.market.app.marketAPI.dto.LoginResponseDTO;
import com.market.app.marketAPI.entity.Usuarios;
import com.market.app.marketAPI.repository.interfaces.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequest){

        System.out.println(loginRequest.toString());

        Usuarios findUser = usuariosRepository.findByUsuarioAndPassword
                (loginRequest.getUsuario(), loginRequest.getPassword());

        if(findUser != null) {

            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setMessage("Usuario autenticado correctamente");
            return ResponseEntity.ok().body(responseDTO);

        }

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setMessage("Usuario o contraseña inválida");
        return ResponseEntity.badRequest().body(responseDTO);
    }

}


