package com.market.app.marketAPI.repository.interfaces;

import com.market.app.marketAPI.entity.Productos;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface IProductosRepository extends CrudRepository<Productos, BigInteger> {
}
