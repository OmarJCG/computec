package com.computec.computec.service.impl;

import com.computec.computec.model.Producto;
import com.computec.computec.dao.IProductoDao;
import com.computec.computec.service.IProductoService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoDao productoRepository;

    private final String rutaDirectorioImagenes = "images//";

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> findAllByCategoria(String categoria) {
        return productoRepository.findAllByCategoria(categoria);
    }

    @Override
    public Producto guardarProductoConImagen(Producto producto, MultipartFile imagen) throws IOException {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no es válido");
        }

        if (imagen != null && !imagen.isEmpty()) {
            // Generamos un nombre único para la imagen usando la marca del producto y el timestamp
            String nombreImagen = producto.getCategoria() + "_" + System.currentTimeMillis() + ".jpg";
            File archivoImagen = new File(rutaDirectorioImagenes + nombreImagen);

            // Guardamos la imagen en el directorio especificado
            FileUtils.copyInputStreamToFile(imagen.getInputStream(), archivoImagen);

            // Actualizamos la ruta de la imagen en el producto
            producto.setImg(nombreImagen);
        } else {
            // Si no hay imagen, asignamos la ruta de la imagen predeterminada
            producto.setImg("default.jpg");
        }

        return productoRepository.save(producto);
    }

    @Override
    public void eliminarImagenProducto(Integer productoId) throws IOException {
        Optional<Producto> productoOpt = productoRepository.findById(productoId);

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            String rutaImagen = rutaDirectorioImagenes + producto.getImg();

            // Verificamos que la ruta de la imagen no sea la de la imagen predeterminada antes de eliminar
            if (rutaImagen != null && !rutaImagen.endsWith("default.jpg")) {
                File archivoImagen = new File(rutaImagen);

                if (archivoImagen.exists()) {
                    FileUtils.forceDelete(archivoImagen);
                }
            }

            // Eliminamos el producto del repositorio
            productoRepository.delete(producto);
        } else {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + productoId);
        }
    }

    @Override
    public Producto actualizarProductoConImagen(Integer productoId, Producto nuevosDatos, MultipartFile nuevaImagen) throws IOException {
        Optional<Producto> productoOpt = productoRepository.findById(productoId);

        if (!productoOpt.isPresent()) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + productoId);
        }

        Producto productoExistente = productoOpt.get();

        // Si hay una nueva imagen, eliminamos la anterior (si no es la predeterminada) y guardamos la nueva
        if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
            String rutaImagenAntigua = rutaDirectorioImagenes + productoExistente.getImg();

            if (rutaImagenAntigua != null && !rutaImagenAntigua.endsWith("default.jpg")) {
                File archivoImagenAntigua = new File(rutaImagenAntigua);
                if (archivoImagenAntigua.exists()) {
                    FileUtils.forceDelete(archivoImagenAntigua);
                }
            }

            // Guardar la nueva imagen
            String nombreImagenNueva = nuevosDatos.getCategoria() + "_" + System.currentTimeMillis() + ".jpg";
            File archivoImagenNueva = new File(rutaDirectorioImagenes + nombreImagenNueva);
            FileUtils.copyInputStreamToFile(nuevaImagen.getInputStream(), archivoImagenNueva);

            // Actualizar la ruta de la imagen en el producto
            productoExistente.setImg(nombreImagenNueva);
        }

        // Actualizar otros campos del producto
        productoExistente.setCategoria(nuevosDatos.getCategoria());
        productoExistente.setMarca(nuevosDatos.getMarca());
        productoExistente.setModelo(nuevosDatos.getModelo());
        productoExistente.setPrecio(nuevosDatos.getPrecio());
        productoExistente.setStock(nuevosDatos.getStock());
        productoExistente.setDetalleProducto(nuevosDatos.getDetalleProducto());

        return productoRepository.save(productoExistente);
    }
}

