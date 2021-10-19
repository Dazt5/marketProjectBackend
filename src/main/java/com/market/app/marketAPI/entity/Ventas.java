package com.market.app.marketAPI.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private BigInteger codigoVenta;

    @Column(name = "cedula_cliente")
    private BigInteger cedulaCliente;

    @Column(name = "cedula_usuario")
    private BigInteger cedulaUsuario;

    @Column(name = "ivaventa")
    private Double ivaVenta;

    @Column(name = "total_venta")
    private Double totalVenta;

    @Column(name = "valor_venta")
    private Double valorVenta;

    @ManyToOne
    @MapsId("cedulaCliente")
    @JoinColumn(name = "cedula_cliente", insertable = false, updatable = false)
    private Clientes clientes;

    @ManyToOne
    @MapsId("cedulaUsuario")
    @JoinColumn(name = "cedula_usuario", insertable = false, updatable = false)
    private Usuarios usuario;

    public BigInteger getCodigo_venta() {
        return codigoVenta;
    }

    public void setCodigoVenta(BigInteger codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public BigInteger getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(BigInteger cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public BigInteger getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(BigInteger cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public Double getIvaVenta() {
        return ivaVenta;
    }

    public void setIvaVenta(Double ivaVenta) {
        this.ivaVenta = ivaVenta;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Ventas{" +
                "codigoVenta=" + codigoVenta +
                ", cedulaCliente=" + cedulaCliente +
                ", cedulaUsuario=" + cedulaUsuario +
                ", ivaVenta=" + ivaVenta +
                ", totalVenta=" + totalVenta +
                ", valorVenta=" + valorVenta +
                '}';
    }
}
