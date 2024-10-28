package com.computec.computec.dao;

import com.computec.computec.model.Compra;
import com.computec.computec.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompraDao extends JpaRepository<Compra, Integer> {
    List<Compra> findByUsuario (Usuario usuario);
}
