package com.market.app.marketAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @Column(name = "codigo_producto")
    private BigInteger codigo_producto;

    @Column(name = "ivacompra")
    private BigDecimal ivacompra;

    @Column(name = "nitproveedor")
    private BigInteger nitproveedor;

    @Column(name = "nombre_producto")
    private String nombre_producto;

    @Column(name = "precio_compra")
    private BigDecimal precio_compra;

    @Column(name = "precio_venta")
    private BigDecimal precio_venta;

    public Productos() {
    }

    public Productos(BigInteger codigo_producto, BigDecimal ivacompra, BigInteger nitproveedor, String nombre_producto, BigDecimal precio_compra, BigDecimal precio_venta) {
        this.codigo_producto = codigo_producto;
        this.ivacompra = ivacompra;
        this.nitproveedor = nitproveedor;
        this.nombre_producto = nombre_producto;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
    }

    public BigInteger getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(BigInteger codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public BigDecimal getIvacompra() {
        return ivacompra;
    }

    public void setIvacompra(BigDecimal ivacompra) {
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

    public BigDecimal getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(BigDecimal precio_compra) {
        this.precio_compra = precio_compra;
    }

    public BigDecimal getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(BigDecimal precio_venta) {
        this.precio_venta = precio_venta;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "codigo_producto=" + codigo_producto +
                ", ivacompra=" + ivacompra +
                ", nitproveedor=" + nitproveedor +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", precio_compra=" + precio_compra +
                ", precio_venta=" + precio_venta +
                '}';
    }
}
