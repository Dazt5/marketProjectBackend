package com.market.app.marketAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.market.app.marketAPI.entity.Usuarios;
import javax.validation.constraints.NotBlank;

import java.math.BigInteger;

public class UsuarioRequestDTO {

    @JsonProperty("cedulaUsuario")
    private BigInteger cedulaUsuario;

    @NotBlank(message = "El email es obligatorio")
    @JsonProperty("emailUsuario")
    private String emailUsuario;

    @NotBlank(message = "El nombre del usuario es obligatorio")
    @JsonProperty("nombreUsuario")
    private String nombreUsuario;

    @NotBlank(message = "El usuario es obligatorio")
    @JsonProperty("usuario")
    private String usuario;

    @NotBlank(message = "El password es obligatorio")
    @JsonProperty("password")
    private String password;

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

    @JsonIgnore
    public Usuarios toEntity(UsuarioRequestDTO request){
        return new Usuarios(
                request.cedulaUsuario,
                request.emailUsuario,
                request.nombreUsuario,
                request.usuario,
                request.password
        );
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "cedulaUsuario=" + cedulaUsuario +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", password='" + password + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
