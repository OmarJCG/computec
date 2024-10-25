package com.computec.computec.service.impl;

import com.computec.computec.dao.IDetalleProductoDao;
import com.computec.computec.model.DetalleProducto;
import com.computec.computec.service.IDetalleProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetalleProductoService implements IDetalleProductoService {

    @Autowired
    private IDetalleProductoDao detalleProductoDao;

    @Override
    public DetalleProducto save(DetalleProducto producto) {
        return detalleProductoDao.save(producto);
    }

    @Override
    public Optional<DetalleProducto> get(Integer id) {
        return detalleProductoDao.findById(id);
    }

    @Override
    public void update(DetalleProducto producto) {
        detalleProductoDao.save(producto);
    }

    @Override
    public void delete(Integer id) {
        detalleProductoDao.deleteById(id);
    }






}
