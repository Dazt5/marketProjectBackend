package com.market.app.marketAPI.services;

import com.market.app.marketAPI.dto.ClienteRequestDTO;
import com.market.app.marketAPI.entity.Clientes;
import com.market.app.marketAPI.entity.Usuarios;
import com.market.app.marketAPI.repository.interfaces.IClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private IClientesRepository clientesRepository;

    public ArrayList<Clientes> getAll(){
        return (ArrayList<Clientes>) clientesRepository.findAll();
    }

    public Optional<Clientes> getById(BigInteger id){
        return clientesRepository.findById(id);
    }

    public Clientes create(ClienteRequestDTO cliente){
        return clientesRepository.save(cliente.toEntity());
    }

    public Clientes update(Clientes cliente, BigInteger id) {

        Clientes actualClient = clientesRepository.findById(id).orElse(null);

        if(actualClient == null) return null;

        actualClient.setDireccion_cliente(cliente.getDireccion_cliente());
        actualClient.setNombre_cliente(cliente.getNombre_cliente());
        actualClient.setTelefono_cliente(cliente.getTelefono_cliente());
        actualClient.setEmail_cliente(cliente.getEmail_cliente());

        return clientesRepository.save(actualClient);
    }

    public void delete(BigInteger id){
        clientesRepository.deleteById(id);
    }

}
