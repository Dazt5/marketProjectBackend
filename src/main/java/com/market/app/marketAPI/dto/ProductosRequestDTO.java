package com.market.app.marketAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.market.app.marketAPI.entity.Productos;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class ProductosRequestDTO {

    @JsonProperty("codigo_producto")
    private BigInteger codigo_producto;

    @NotNull(message = "Debe ingresar un iva")
    @JsonProperty("ivacompra")
    private Double ivacompra;

    @NotNull(message = "Debe seleccionar un proveedor")
    @JsonProperty("nitproveedor")
    private BigInteger nitproveedor;

    @NotNull(message = "Debe ingresar un nombre al producto")
    @JsonProperty("nombre_producto")
    private String nombre_producto;

    @NotNull(message = "Debe ingresar el precio de compra")
    @JsonProperty("precio_compra")
    private Double precio_compra;

    @NotNull(message = "Debe ingresar el precio de venta")
    @JsonProperty("precio_venta")
    private Double precio_venta;

    public Productos toEntity(){
        return new Productos(
                this.codigo_producto,
                this.ivacompra,
                this.nitproveedor,
                this.nombre_producto,
                this.precio_compra,
                this.precio_venta
        );
    }

    public BigInteger getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(BigInteger codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public Double getIvacompra() {
        return ivacompra;
    }

    public void setIvacompra(Double ivacompra) {
        this.ivacompra = ivacompra;
    }

    public BigInteger getNitproveedor() {
        return nitproveedor;
    }

    public void setNitproveedor(BigInteger nitproveedor) {
        this.nitproveedor = nitproveedor;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(Double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }
}
