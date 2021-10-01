package com.market.app.marketAPI.services;

import com.market.app.marketAPI.dto.UsuarioRequestDTO;
import com.market.app.marketAPI.entity.Usuarios;
import com.market.app.marketAPI.repository.interfaces.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class UsuariosService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    public ArrayList<Usuarios> getAll() {
        return (ArrayList<Usuarios>) usuariosRepository.findAll();
    }

    public Optional<Usuarios> getById(BigInteger id) {
        return usuariosRepository.findById(id);
    }

    public Usuarios create(UsuarioRequestDTO usuario) {
        return usuariosRepository.save(usuario.toEntity(usuario));
    }

    public Usuarios update(Usuarios newUser, BigInteger id) {
        Usuarios actualUser = usuariosRepository.findById(id).orElse(null);
        if (actualUser == null){
            return null;
        }
        actualUser.setUsuario(newUser.getUsuario());
        actualUser.setEmailUsuario(newUser.getEmailUsuario());
        actualUser.setNombreUsuario(newUser.getNombreUsuario());
        actualUser.setPassword(newUser.getPassword());

        return usuariosRepository.save(actualUser);
    }

    public void delete(BigInteger id) {
        usuariosRepository.deleteById(id);
    }
}
