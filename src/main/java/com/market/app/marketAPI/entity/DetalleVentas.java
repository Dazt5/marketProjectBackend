package com.market.app.marketAPI.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVentas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private BigInteger codigoDetalleVenta;

    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;

    @Column(name = "codigo_producto")
    private BigInteger codigoProducto;

    @Column(name = "codigo_venta")
    private BigInteger codigoVenta;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "valor_venta")
    private Double valorVenta;

    @Column(name = "valoriva")
    private Double valorIva;

    @ManyToOne
    @MapsId("codigoProducto")
    @JoinColumn(name = "codigo_producto", insertable = false, updatable = false)
    private Productos producto;

    @ManyToOne
    @MapsId("codigoVenta")
    @JoinColumn(name = "codigo_venta", insertable = false, updatable = false)
    private Ventas venta;

    public BigInteger getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(BigInteger codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public BigInteger getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(BigInteger codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public BigInteger getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(BigInteger codigoVenta) {
        this.codigoVenta = codigoVenta;
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

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }
}
