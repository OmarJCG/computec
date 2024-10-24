package com.computec.computec.controller;

import com.computec.computec.service.IProductoService;
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

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @GetMapping("")
    public String home(Model model, HttpSession session) {

        //Logback
        LOGGER.info("sesión de usuario: {}", session.getAttribute("usuario"));

        model.addAttribute("usuario", session.getAttribute("usuario"));

        return "usuario/inicio";
    }

    @GetMapping("/show/{categoria}")
    public String showProductosr(@PathVariable String categoria, Model model, HttpSession session){

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

}
