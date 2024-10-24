package com.computec.computec.controller;

import com.computec.computec.model.DetalleProducto;
import com.computec.computec.model.Producto;
import com.computec.computec.service.IDetalleProductoService;
import com.computec.computec.service.IProductoService;
import com.computec.computec.service.IUsuarioService;
import com.computec.computec.service.impl.UploadFileSerivice;
import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/producto")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private UploadFileSerivice upload;

    @Autowired
    private IDetalleProductoService detalleProductoService;

    @GetMapping("/show/{categoria}")
    public String showProducto(@PathVariable String categoria, Model model, HttpSession session){

        //Logback
        LOGGER.info("Categoria enviada : {}", categoria);

        // Google Guava
        ImmutableMap<String, String> productosPorCategoria = ImmutableMap.of(
                "procesador","procesador",
                "ram","ram",
                "ssd","ssd",
                "hhd","hhd",
                "tarjeta-madre","tmadre",
                "tarjeta-grafica","tgrafica",
                "disipador","disipador",
                "fuente-poder","fpoder",
                "case", "case"
        );

        model.addAttribute("productos", productoService.findAllByCategoria(productosPorCategoria.get(categoria)));

        model.addAttribute("usuario", session.getAttribute("usuario"));

        return "producto/show";
    }


    @GetMapping("/crear")
    public String crear(Model model, HttpSession session){

        model.addAttribute("usuario", session.getAttribute("usuario"));


        return "producto/crear";
    }

    @PostMapping("/save")
    private String save(Producto producto, DetalleProducto detalleProducto, @RequestParam("image") MultipartFile file) throws IOException {

        //Logback
        LOGGER.info("producto del formulario: {}", producto);
        LOGGER.info("file img del formulario: {}", file);
        LOGGER.info("detalle producto del formulario: {}", detalleProducto);

        // Google Guava
        ImmutableMap<String, String> productosPorCategoria = ImmutableMap.of(
                "procesador","procesador",
                "ram","ram",
                "ssd","ssd",
                "hhd","hhd",
                "tmadre","tarjeta-madre",
                "tgrafica","tarjeta-grafica",
                "disipador","disipador",
                "fpoder","fuente-poder",
                "case", "case"
        );

        if (producto.getId()==null) { // cuando se crea un producto
            String nombreImagen= upload.saveImage(file);
            producto.setImg(nombreImagen);
        }

        DetalleProducto dp =  detalleProductoService.save(detalleProducto);

        producto.setDetalleProducto(dp);

        productoService.save(producto);

        String ruta = "redirect:/producto/show/"+productosPorCategoria.get(producto.getCategoria());

        return ruta;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, HttpSession session){

        model.addAttribute("usuario", session.getAttribute("usuario"));

        Optional<Producto> optionalProducto = productoService.get(id);

        Producto producto = optionalProducto.get();

        //Logback
        LOGGER.info("producto de la base de datos : {}", producto);

        model.addAttribute("p", producto);

        String categoria= producto.getCategoria();

        // Google Guava
        ImmutableMap<String, String> productosPorCategoria = ImmutableMap.of(
                "procesador","procesador",
                "ram","ram",
                "ssd","ssd",
                "hhd","hhd",
                "tmadre","tarjeta-madre",
                "tgrafica","tarjeta-grafica",
                "disipador","disipador",
                "fpoder","fuente-poder",
                "case", "case"
        );

        model.addAttribute("categoria", productosPorCategoria.get(categoria));

        return "producto/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("image") MultipartFile file) throws IOException {

        //Logback
        LOGGER.info("producto formulario modificar: {}", producto);

        String ruta = "";

        Producto p= new Producto();
        p=productoService.get(producto.getId()).get();

        // Google Guava
        ImmutableMap<String, String> productosPorCategoria = ImmutableMap.of(
                "procesador","procesador",
                "ram","ram",
                "ssd","ssd",
                "hhd","hhd",
                "tarjeta-madre","tmadre",
                "tarjeta-grafica","tgrafica",
                "disipador","disipador",
                "fuente-poder","fpoder",
                "case", "case"
        );

        if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagem
            producto.setImg(p.getImg());

        }else {// cuando se edita tbn la imagen
            //eliminar cuando no sea la imagen por defecto
            if (!p.getImg().equals("default.jpg")) {
                upload.deleteImage(p.getImg());
            }
            String nombreImagen= upload.saveImage(file);
            producto.setImg(nombreImagen);
        }


        producto.setCategoria(productosPorCategoria.get(producto.getCategoria()));
        ruta = "redirect:/producto/show/"+producto.getCategoria();

        productoService.update(producto);

        return ruta;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        Optional<Producto>  producto = productoService.get(id);

        String ruta = "";

        // Google Guava
        ImmutableMap<String, String> productosPorCategoria = ImmutableMap.of(
                "procesador","procesador",
                "ram","ram",
                "ssd","ssd",
                "hhd","hhd",
                "tmadre","tarjeta-madre",
                "tgrafica","tarjeta-grafica",
                "disipador","disipador",
                "fpoder","fuente-poder",
                "case", "case"
        );

        Producto p= new Producto();
        p=productoService.get(id).get();

        //eliminar cuando no sea la imagen por defecto
        if (!p.getImg().equals("default.jpg")) {
            upload.deleteImage(p.getImg());
        }


        productoService.delete(id);

        return "redirect:/producto/show/"+productosPorCategoria.get(producto.get().getCategoria());

    }
}
