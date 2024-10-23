package com.computec.computec.controller;

import com.computec.computec.model.Producto;
import com.computec.computec.model.Usuario;
import com.computec.computec.service.IProductoService;
import com.computec.computec.service.IUsuarioService;
import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/producto")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IProductoService productoService;

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
    private String save(Producto producto, HttpSession session){

        //Logback
        LOGGER.info("producto del formulario: {}", producto);

        productoService.save(producto);

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
    public String update(Producto producto){

        //Logback
        LOGGER.info("producto formulario modificar: {}", producto);

        String ruta = "";

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


        productoService.delete(id);

        return "redirect:/producto/show/"+productosPorCategoria.get(producto.get().getCategoria());

    }
}
