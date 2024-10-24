package com.computec.computec.service;



import com.computec.computec.model.DetalleProducto;

import java.util.List;
import java.util.Optional;

public interface IDetalleProductoService {
    DetalleProducto save(DetalleProducto producto);
    Optional<DetalleProducto> get(Integer id);
    void update(DetalleProducto producto);
    void delete(Integer id);
}
