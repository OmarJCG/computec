package com.computec.computec.controller;

import com.computec.computec.service.IProductoService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @GetMapping("")
    public String home(Model model, HttpSession session) {

        //Logback
        logger.info("sesión de usuario: {}", session.getAttribute("usuario"));

        model.addAttribute("usuario", session.getAttribute("usuario"));

        return "usuario/inicio";
    }

    @GetMapping("/procesador")
    public String catProcesador(Model model, HttpSession session){

        model.addAttribute("productos", productoService.findAllByCategoria("procesador"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "c1");
        model.addAttribute("c2", "");
        model.addAttribute("c3", "");
        model.addAttribute("c4", "");
        model.addAttribute("c5", "");
        model.addAttribute("c6", "");
        model.addAttribute("c7", "");
        model.addAttribute("c8", "");
        model.addAttribute("c9", "");

        return "usuario/catalogo";
    }

    @GetMapping("/ram")
    public String showRam(Model model, HttpSession session){

        model.addAttribute("productos", productoService.findAllByCategoria("ram"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "");
        model.addAttribute("c2", "c2");
        model.addAttribute("c3", "");
        model.addAttribute("c4", "");
        model.addAttribute("c5", "");
        model.addAttribute("c6", "");
        model.addAttribute("c7", "");
        model.addAttribute("c8", "");
        model.addAttribute("c9", "");
        return "usuario/catalogo";
    }

    @GetMapping("/ssd")
    public String showSSD(Model model, HttpSession session){

        model.addAttribute("productos", productoService.findAllByCategoria("ssd"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "");
        model.addAttribute("c2", "");
        model.addAttribute("c3", "c3");
        model.addAttribute("c4", "");
        model.addAttribute("c5", "");
        model.addAttribute("c6", "");
        model.addAttribute("c7", "");
        model.addAttribute("c8", "");
        model.addAttribute("c9", "");

        return "usuario/catalogo";
    }

    @GetMapping("/hhd")
    public String showHHD(Model model, HttpSession session){

        model.addAttribute("productos", productoService.findAllByCategoria("hhd"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "");
        model.addAttribute("c2", "");
        model.addAttribute("c3", "");
        model.addAttribute("c4", "c4");
        model.addAttribute("c5", "");
        model.addAttribute("c6", "");
        model.addAttribute("c7", "");
        model.addAttribute("c8", "");
        model.addAttribute("c9", "");
        return "usuario/catalogo";
    }

    @GetMapping("/tarjeta-madre")
    public String showTMadre(Model model, HttpSession session){
        model.addAttribute("productos", productoService.findAllByCategoria("tmadre"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "");
        model.addAttribute("c2", "");
        model.addAttribute("c3", "");
        model.addAttribute("c4", "");
        model.addAttribute("c5", "c5");
        model.addAttribute("c6", "");
        model.addAttribute("c7", "");
        model.addAttribute("c8", "");
        model.addAttribute("c9", "");
        return "usuario/catalogo";
    }

    @GetMapping("/tarjeta-grafica")
    public String showTGrafica(Model model, HttpSession session){
        model.addAttribute("productos", productoService.findAllByCategoria("tgrafica"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "");
        model.addAttribute("c2", "");
        model.addAttribute("c3", "");
        model.addAttribute("c4", "");
        model.addAttribute("c5", "");
        model.addAttribute("c6", "c6");
        model.addAttribute("c7", "");
        model.addAttribute("c8", "");
        model.addAttribute("c9", "");
        return "usuario/catalogo";
    }

    @GetMapping("/disipador")
    public String showDisipador(Model model, HttpSession session){
        model.addAttribute("productos", productoService.findAllByCategoria("disipador"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "");
        model.addAttribute("c2", "");
        model.addAttribute("c3", "");
        model.addAttribute("c4", "");
        model.addAttribute("c5", "");
        model.addAttribute("c6", "");
        model.addAttribute("c7", "c7");
        model.addAttribute("c8", "");
        model.addAttribute("c9", "");
        return "usuario/catalogo";
    }

    @GetMapping("/fuente-poder")
    public String showFpoder(Model model, HttpSession session){
        model.addAttribute("productos", productoService.findAllByCategoria("fpoder"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "");
        model.addAttribute("c2", "");
        model.addAttribute("c3", "");
        model.addAttribute("c4", "");
        model.addAttribute("c5", "");
        model.addAttribute("c6", "");
        model.addAttribute("c7", "");
        model.addAttribute("c8", "c8");
        model.addAttribute("c9", "");
        return "usuario/catalogo";
    }

    @GetMapping("/case")
    public String showCase(Model model, HttpSession session){
        model.addAttribute("productos", productoService.findAllByCategoria("case"));
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("c1", "");
        model.addAttribute("c2", "");
        model.addAttribute("c3", "");
        model.addAttribute("c4", "");
        model.addAttribute("c5", "");
        model.addAttribute("c6", "");
        model.addAttribute("c7", "");
        model.addAttribute("c8", "");
        model.addAttribute("c9", "c9");
        return "usuario/catalogo";
    }



}
