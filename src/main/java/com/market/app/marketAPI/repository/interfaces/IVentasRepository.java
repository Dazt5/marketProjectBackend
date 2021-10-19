package com.market.app.marketAPI.repository.interfaces;

import com.market.app.marketAPI.entity.Ventas;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;
import java.util.ArrayList;

public interface IVentasRepository extends CrudRepository<Ventas, BigInteger> {

    ArrayList<Ventas> findByCedulaCliente(BigInteger cedulaCliente);

}
