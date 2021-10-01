package com.market.app.marketAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name="proveedores")
public class Proveedores {

    @Id
    @Column(name = "nitproveedor")
    private BigInteger nitproveedor;

    @Column(name = "ciudad_proveedor")
    private String ciudad_proveedor;

    @Column(name = "direccion_proveedor")
    private String direccion_proveedor;

    @Column(name = "nombre_proveedor")
    private String nombre_proveedor;

    @Column(name = "telefono_proveedor")
    private String telefono_proveedor;

    public Proveedores() {
    }

    public Proveedores(BigInteger nitproveedor, String ciudad_proveedor, String direccion_proveedor, String nombre_proveedor, String telefono_proveedor) {
        this.nitproveedor = nitproveedor;
        this.ciudad_proveedor = ciudad_proveedor;
        this.direccion_proveedor = direccion_proveedor;
        this.nombre_proveedor = nombre_proveedor;
        this.telefono_proveedor = telefono_proveedor;
    }

    public BigInteger getNitproveedor() {
        return nitproveedor;
    }

    public void setNitproveedor(BigInteger nitproveedor) {
        this.nitproveedor = nitproveedor;
    }

    public String getCiudad_proveedor() {
        return ciudad_proveedor;
    }

    public void setCiudad_proveedor(String ciudad_proveedor) {
        this.ciudad_proveedor = ciudad_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }
}
