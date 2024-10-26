package com.computec.computec.dao;

import com.computec.computec.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleOrdenDao extends JpaRepository<DetalleOrden, Integer> {
}
