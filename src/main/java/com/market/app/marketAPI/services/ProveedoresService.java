package com.market.app.marketAPI.services;

import com.market.app.marketAPI.dto.ProveedoresRequestDTO;
import com.market.app.marketAPI.entity.Proveedores;
import com.market.app.marketAPI.repository.interfaces.IProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProveedoresService {

    @Autowired
    private IProveedoresRepository proveedoresRepository;

    public ArrayList<Proveedores> getAll() {
        return (ArrayList<Proveedores>) proveedoresRepository.findAll();
    }

    public Optional<Proveedores> getById(BigInteger id) {
        return proveedoresRepository.findById(id);
    }

    public Proveedores create(ProveedoresRequestDTO proveedor) {
        return proveedoresRepository.save(proveedor.toEntity());
    }

    public Proveedores update(Proveedores proveedor, BigInteger id) {
        Proveedores actualProveedor = proveedoresRepository.findById(id).orElse(null);

        if (actualProveedor == null) return null;

        actualProveedor.setNombre_proveedor(proveedor.getNombre_proveedor());
        actualProveedor.setCiudad_proveedor(proveedor.getCiudad_proveedor());
        actualProveedor.setTelefono_proveedor(proveedor.getTelefono_proveedor());
        actualProveedor.setDireccion_proveedor(proveedor.getDireccion_proveedor());

        return proveedoresRepository.save(actualProveedor);
    }

    public void delete(BigInteger id) {
        proveedoresRepository.deleteById(id);
    }

}
