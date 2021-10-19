package com.market.app.marketAPI.repository.interfaces;

import com.market.app.marketAPI.entity.DetalleVentas;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.ArrayList;

public interface IDetalleVentasRepository extends CrudRepository<DetalleVentas, BigInteger> {

    ArrayList<DetalleVentas> findByCodigoVenta(BigInteger codigoVenta);

}
