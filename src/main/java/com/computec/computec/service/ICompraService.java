package com.computec.computec.service;

import com.computec.computec.model.Compra;
import com.computec.computec.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface ICompraService {
    List<Compra> findAll();
    Optional<Compra> findById(Integer id);
    Compra save (Compra orden);
    String generarNumeroOrden();
    List<Compra> findByUsuario (Usuario usuario);
}
