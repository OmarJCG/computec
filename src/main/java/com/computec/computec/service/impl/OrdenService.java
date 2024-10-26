package com.computec.computec.service.impl;

import com.computec.computec.dao.IOrdenDao;
import com.computec.computec.model.Orden;
import com.computec.computec.model.Usuario;
import com.computec.computec.service.IOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService implements IOrdenService {

    @Autowired
    private IOrdenDao ordenDao;

    @Override
    public List<Orden> findAll() {
        return List.of();
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Orden save(Orden orden) {
        return ordenDao.save(orden);
    }

    @Override
    public String generarNumeroOrden() {
        return "";
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return List.of();
    }
}
