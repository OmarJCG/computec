package com.computec.computec.service;

import com.computec.computec.model.Producto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    Producto save(Producto producto);
    Optional<Producto> get(Integer id);
    void update(Producto producto);
    void delete(Integer id);
    List<Producto> findAllByCategoria(String categoria);
    Producto guardarProductoConImagen(Producto producto, MultipartFile imagen) throws IOException;
    void eliminarImagenProducto(Integer productoId) throws IOException;
    public Producto actualizarProductoConImagen(Integer productoId, Producto nuevosDatos, MultipartFile nuevaImagen) throws IOException;
}
