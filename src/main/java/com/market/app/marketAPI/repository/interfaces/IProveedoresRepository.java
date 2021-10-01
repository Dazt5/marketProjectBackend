package com.market.app.marketAPI.repository.interfaces;

import com.market.app.marketAPI.entity.Proveedores;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface IProveedoresRepository extends CrudRepository<Proveedores, BigInteger> {
}
