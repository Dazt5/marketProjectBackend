package com.market.app.marketAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @Column(name = "cedula_cliente")
    private BigInteger cedula_cliente;

    @Column(name = "direccion_cliente")
    private String direccion_cliente;

    @Column(name = "email_cliente")
    private String email_cliente;

    @Column(name = "nombre_cliente")
    private String nombre_cliente;

    @Column(name = "telefono_cliente")
    private String telefono_cliente;

    public Clientes(BigInteger cedula_cliente, String direccion_cliente, String email_cliente, String nombre_cliente, String telefono_cliente) {
        this.cedula_cliente = cedula_cliente;
        this.direccion_cliente = direccion_cliente;
        this.email_cliente = email_cliente;
        this.nombre_cliente = nombre_cliente;
        this.telefono_cliente = telefono_cliente;
    }

    public Clientes() {
    }

    public BigInteger getCedula_cliente() {
        return cedula_cliente;
    }

    public void setCedula_cliente(BigInteger cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }
}
