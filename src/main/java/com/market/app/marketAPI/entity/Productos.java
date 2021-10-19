package com.market.app.marketAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @Column(name = "codigo_producto")
    private BigInteger codigo_producto;

    @Column(name = "ivacompra")
    private Double ivacompra;

    @Column(name = "nitproveedor")
    private BigInteger nitproveedor;

    @Column(name = "nombre_producto")
    private String nombre_producto;

    @Column(name = "precio_compra")
    private Double precio_compra;

    @Column(name = "precio_venta")
    private Double precio_venta;

    public Productos() {
    }

    public Productos(BigInteger codigo_producto, Double ivacompra, BigInteger nitproveedor, String nombre_producto, Double precio_compra, Double precio_venta) {
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
