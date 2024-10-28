package com.computec.computec.dao;

import com.computec.computec.model.Compra;
import com.computec.computec.model.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleCompraDao extends JpaRepository<DetalleCompra, Integer> {
    List<DetalleCompra> findByOrdenId (Integer ordenId);

}
