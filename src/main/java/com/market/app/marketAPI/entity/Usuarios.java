package com.market.app.marketAPI.entity;

import org.hibernate.type.BigIntegerType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name="usuarios")
public class Usuarios {

    @Id
    @Column(name = "cedula_usuario")
    private BigInteger cedulaUsuario;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "password")
    private String password;

    @Column(name = "usuario")
    private String usuario;

    public BigInteger getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(BigInteger cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
