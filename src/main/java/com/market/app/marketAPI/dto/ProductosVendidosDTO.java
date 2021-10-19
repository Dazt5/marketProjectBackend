package com.market.app.marketAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class ProductosVendidosDTO {

    @JsonProperty("codigoProducto")
    @NotNull(message = "El código de producto no puede ir vacio.")
    private BigInteger codigoProducto;

    @JsonProperty("cantidadProducto")
    @NotNull(message = "El cantidad no puede ir vacia.")
    private Integer cantidadProducto;

    @JsonProperty("valorTotal")
    @NotNull(message = "El valor total no puede ir vacio.")
    private Double valorTotal;

    @JsonProperty("valorVenta")
    @NotNull(message = "El valor unitario no puede ir vacio.")
    private Double valorVenta;

    @JsonProperty("valorIva")
    @NotNull(message = "El valor unitario del IVA no puede ir vacio.")
    private Double valorIva;

    public BigInteger getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(BigInteger codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Double getValorIva() {
        return valorIva;
    }

    public void setValorIva(Double valorIva) {
        this.valorIva = valorIva;
    }

    @Override
    public String toString() {
        return "ProductosVendidosDTO{" +
                "codigoProducto=" + codigoProducto +
                ", cantidadProducto=" + cantidadProducto +
                ", valorTotal=" + valorTotal +
                ", valorVenta=" + valorVenta +
                ", valorIva=" + valorIva +
                '}';
    }
}
