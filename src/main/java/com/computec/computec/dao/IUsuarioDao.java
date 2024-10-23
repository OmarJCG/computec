package com.computec.computec.dao;

import com.computec.computec.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
}
