package com.market.app.marketAPI.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.market.app.marketAPI.entity.Proveedores;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

public class ProveedoresRequestDTO {

    @JsonProperty("nitProveedor")
    private BigInteger nitProveedor;

    @NotBlank(message = "La ciudad es obligatoria")
    @JsonProperty("ciudad_proveedor")
    private String ciudad_proveedor;

    @NotBlank(message = "La direccion es obligatoria")
    @JsonProperty("direccion_proveedor")
    private String direccion_proveedor;

    @NotBlank(message = "El nombre es obligatorio")
    @JsonProperty("nombre_proveedor")
    private String nombre_proveedor;

    @NotBlank(message = "El telefono es obligatorio")
    @JsonProperty("telefono_proveedor")
    private String telefono_proveedor;

    public Proveedores toEntity(){
        return new Proveedores(
                this.getNitProveedor(),
                this.getCiudad_proveedor(),
                this.getDireccion_proveedor(),
                this.getNombre_proveedor(),
                this.getTelefono_proveedor()
        );
    }

    public BigInteger getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(BigInteger nitProveedor) {
        this.nitProveedor = nitProveedor;
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
