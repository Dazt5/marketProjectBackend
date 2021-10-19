package com.market.app.marketAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;

public class VentaRequestDTO {

    @JsonProperty("cedulaCliente")
    @NotNull(message = "La cédula del cliente no puede ir vacia.")
    private BigInteger cedulaCliente;

    @JsonProperty("totalVenta")
    @NotNull(message = "El valor total de la venta no puede ir vacio.")
    private Double totalVenta;

    @JsonProperty("subTotal")
    @NotNull(message = "El subtotal no puede ir vacio.")
    private Double subTotal;

    @JsonProperty("totalIva")
    @NotNull(message = "El valor total del IVA no puede ir vacio.")
    private Double totalIva;

    @Valid
    private ArrayList<ProductosVendidosDTO> productos;

    public BigInteger getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(BigInteger cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(Double totalIva) {
        this.totalIva = totalIva;
    }

    public ArrayList<ProductosVendidosDTO> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductosVendidosDTO> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "VentaRequestDTO{" +
                "cedulaCliente=" + cedulaCliente +
                ", totalVenta=" + totalVenta +
                ", totalIva=" + totalIva +
                ", productos=" + productos +
                '}';
    }
}
