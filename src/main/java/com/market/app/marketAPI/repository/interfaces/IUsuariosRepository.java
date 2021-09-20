package com.market.app.marketAPI.repository.interfaces;

import com.market.app.marketAPI.entity.Usuarios;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;
import java.util.List;

public interface IUsuariosRepository extends CrudRepository<Usuarios, BigInteger> {

    Usuarios findByUsuarioAndPassword(String usuario, String password);
}
