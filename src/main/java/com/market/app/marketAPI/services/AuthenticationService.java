package com.market.app.marketAPI.services;

import com.market.app.marketAPI.dto.LoginRequestDTO;
import com.market.app.marketAPI.dto.LoginResponseDTO;
import com.market.app.marketAPI.entity.Usuarios;
import com.market.app.marketAPI.repository.interfaces.IUsuariosRepository;
import com.market.app.marketAPI.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    @Autowired
    private JWTManager jwtManager;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequest){

        Usuarios findUser = usuariosRepository.findByUsuarioAndPassword
                (loginRequest.getUsuario(), loginRequest.getPassword());

        if(findUser != null) {
            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setMessage("Usuario autenticado correctamente");
            responseDTO.setToken(jwtManager.getJWTToken(findUser.getUsuario()));
            return ResponseEntity.ok().body(responseDTO);

        }
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setMessage("Usuario o contraseña inválida");
        return ResponseEntity.badRequest().body(responseDTO);
    }
}


