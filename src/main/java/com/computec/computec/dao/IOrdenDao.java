package com.computec.computec.dao;

import com.computec.computec.model.Orden;
import com.computec.computec.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenDao extends JpaRepository<Orden, Integer> {
    List<Orden> findByUsuario (Usuario usuario);
}
