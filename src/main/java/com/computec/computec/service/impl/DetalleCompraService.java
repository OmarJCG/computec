package com.computec.computec.service.impl;

import com.computec.computec.dao.IDetalleCompraDao;
import com.computec.computec.model.DetalleCompra;
import com.computec.computec.service.IDetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCompraService implements IDetalleCompraService {

    @Autowired
    private IDetalleCompraDao detalleOrdenDao;

    @Override
    public DetalleCompra save(DetalleCompra detalleOrden) {
        return detalleOrdenDao.save(detalleOrden);
    }
}
