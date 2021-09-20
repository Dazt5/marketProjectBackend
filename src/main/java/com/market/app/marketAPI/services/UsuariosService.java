package com.market.app.marketAPI.services;

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

    public boolean create(Usuarios usuario) {
        usuariosRepository.save(usuario);
        return true;
        //TODO: GET BY ID TO VERIFY USER HAS CREATED
    }

    public boolean update(Usuarios newUser, BigInteger id) {
        Usuarios oldUser = usuariosRepository.findById(id).orElse(null);

        oldUser.setUsuario(newUser.getUsuario());
        oldUser.setEmailUsuario(newUser.getEmailUsuario());
        oldUser.setNombreUsuario(newUser.getNombreUsuario());
        oldUser.setPassword(newUser.getPassword());

        usuariosRepository.save(oldUser);
        return true;
    }

    public boolean delete(BigInteger id) {
        usuariosRepository.deleteById(id);
        return true;
        //TODO: GET BY ID TO VERIFY USER HAS BEEN DELETED
    }
}
