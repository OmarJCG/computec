package com.computec.computec.service.impl;

import com.computec.computec.dao.IDetalleOrdenDao;
import com.computec.computec.model.DetalleOrden;
import com.computec.computec.service.IDetalleOrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOrdenService implements IDetalleOrdenService {

    @Autowired
    private IDetalleOrdenDao detalleOrdenDao;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenDao.save(detalleOrden);
    }
}
