package com.computec.computec.controller;

import com.computec.computec.model.DetalleProducto;
import com.computec.computec.model.Producto;
import com.computec.computec.service.IDetalleProductoService;
import com.computec.computec.service.IProductoService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IDetalleProductoService detalleProductoService;

    @GetMapping("")
    public String home(Model model, HttpSession session) {

        //Logback
        LOGGER.info("sesión de usuario: {}", session.getAttribute("usuario"));

        model.addAttribute("usuario", session.getAttribute("usuario"));

        return "usuario/inicio";
    }

    @GetMapping("/show/{categoria}")
    public String showProductos(@PathVariable String categoria, Model model, HttpSession session){

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

        return "usuario/catalogo";
    }

    @GetMapping("/info-producto/{id}")
    public String showInfoProducto(@PathVariable Integer id, Model model, HttpSession session){

        model.addAttribute("usuario", session.getAttribute("usuario"));

        Producto producto = productoService.get(id).get();

        DetalleProducto detalleProducto = producto.getDetalleProducto();

        List<String> valores = detalleProducto.getValores();

        ImmutableList<String> atributosDetalle = null;

        switch (producto.getCategoria()){
            case "procesador":
                atributosDetalle = ImmutableList.of("Núcleos", "Hilos", "Frecuencia base", "Frecuencia turbo máxima", "Cache",
                        "Tipo de memoria", "TDP/TDP predeterminado", "Memoria máxima",
                        "Gráficos integrados", "Overclocking", "Versión de PCI Express");
                break;
            case "ram":
                atributosDetalle = ImmutableList.of("Capacidad", "Velocidad", "Tipo de memoria", "Latencia CAS",
                        "Voltaje", "Latencia SPD", "Factor de forma", "Formato", "Temperatura de operación",
                        "Frecuencia máxima", "RGB");
                break;
            case "ssd":
                break;
            case "hhd":
                break;
            case "tmadre":
                break;
            case "tgrafica":
                break;
            case "disipador":
                break;
            case "fpoder":
                break;
            case "case":
                break;

        }

        //Logback
        LOGGER.info("Producto para ver informacion : {}", producto);
        LOGGER.info("valores detalle producto : {}", valores);

        model.addAttribute("p", producto);
        model.addAttribute("valores", valores );
        model.addAttribute("atributoDetalle", atributosDetalle);

        return "usuario/infoproducto";
    }

}
