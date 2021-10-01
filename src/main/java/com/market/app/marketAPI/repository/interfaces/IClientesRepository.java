package com.market.app.marketAPI.repository.interfaces;

import com.market.app.marketAPI.entity.Clientes;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface IClientesRepository extends CrudRepository<Clientes, BigInteger> {
}
