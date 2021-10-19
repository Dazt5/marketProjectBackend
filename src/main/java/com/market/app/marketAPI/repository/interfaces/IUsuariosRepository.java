package com.market.app.marketAPI.repository.interfaces;

import com.market.app.marketAPI.entity.Usuarios;
import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;

public interface IUsuariosRepository extends CrudRepository<Usuarios, BigInteger> {

    Usuarios findByUsuarioAndPassword(String usuario, String password);

    Usuarios findByUsuario(String usuario);
}
