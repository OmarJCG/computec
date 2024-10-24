package com.computec.computec.dao;

import com.computec.computec.model.DetalleProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleProductoDao extends JpaRepository<DetalleProducto, Integer> {
}
