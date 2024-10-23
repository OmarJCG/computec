package com.computec.computec.controller;

import com.computec.computec.model.Producto;
import com.computec.computec.model.Usuario;
import com.computec.computec.service.IProductoService;
import com.computec.computec.service.IUsuarioService;
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

    @GetMapping("/procesador")
    public String showProcesador(Model model, HttpSession session){
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
        return "producto/show";
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

        return "producto/show";
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
        return "producto/show";
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
        return "producto/show";
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
        return "producto/show";
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
        return "producto/show";
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
        return "producto/show";
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
        return "producto/show";
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

        String ruta = "";

        switch (producto.getCategoria()) {
            case "procesador":
                ruta =  "redirect:/producto/procesador";
                break;
            case "ram":
                ruta = "redirect:/producto/ram";
                break;
            case "ssd":
                ruta = "redirect:/producto/ssd";
                break;
            case "hhd":
                ruta ="redirect:/producto/hhd";
                break;
            case "tmadre":
                ruta = "redirect:/producto/tarjeta-madre";
                break;
            case "tgrafica":
                ruta = "redirect:/producto/tarjeta-grafica";
                break;
            case "disipador":
                ruta = "redirect:/producto/disipador";
                break;
            case "fpoder":
                ruta = "redirect:/producto/fuente-poder";
                break;
            case "case":
                ruta = "redirect:/producto/case";
                break;
        }

        return ruta;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, HttpSession session){

        model.addAttribute("usuario", session.getAttribute("usuario"));

        Optional<Producto> optionalProducto = productoService.get(id);

        Producto producto = optionalProducto.get();

        //Logback
        LOGGER.info("producto : {}", producto);

        model.addAttribute("p", producto);

        String categoria= "";


        switch (producto.getCategoria()) {
            case "procesador":
                categoria= "Procesador";
                break;
            case "ram":
                categoria= "Ram";
                break;
            case "ssd":
                categoria= "SSD";
                break;
            case "hhd":
                categoria= "HHD";;
                break;
            case "tmadre":
                categoria= "Tarjeta madre";
                break;
            case "tgrafica":
                categoria= "Tarjeta grafica";
                break;
            case "disipador":
                categoria= "Disipador";
                break;
            case "fpoder":
                categoria= "Fuente de poder";
                break;
            case "case":
                categoria= "Case";
                break;
        }

        model.addAttribute("categoria", categoria);

        return "producto/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto){

        LOGGER.info("producto formulario modificar: {}", producto);

        String ruta = "";

        switch (producto.getCategoria()) {
            case "Procesador":
                producto.setCategoria("procesador");
                ruta =  "redirect:/producto/procesador";
                break;
            case "Ram":
                producto.setCategoria("ram");
                ruta = "redirect:/producto/ram";
                break;
            case "SSD":
                producto.setCategoria("ssd");
                ruta = "redirect:/producto/ssd";
                break;
            case "HHD":
                producto.setCategoria("hhd");
                ruta ="redirect:/producto/hhd";
                break;
            case "Tarjeta madre":
                producto.setCategoria("tmadre");
                ruta = "redirect:/producto/tarjeta-madre";
                break;
            case "Tarjeta grafica":
                producto.setCategoria("tgrafica");
                ruta = "redirect:/producto/tarjeta-grafica";
                break;
            case "Disipador":
                producto.setCategoria("disipador");
                ruta = "redirect:/producto/disipador";
                break;
            case "Fuente de poder":
                producto.setCategoria("fpoder");
                ruta = "redirect:/producto/fuente-poder";
                break;
            case "Case":
                producto.setCategoria("case");
                ruta = "redirect:/producto/case";
                break;
        }

        productoService.update(producto);

        return ruta;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        Optional<Producto>  producto = productoService.get(id);


        String ruta = "";

        switch (producto.get().getCategoria()) {
            case "procesador":
                ruta =  "redirect:/producto/procesador";
                break;
            case "ram":
                ruta = "redirect:/producto/ram";
                break;
            case "ssd":
                ruta = "redirect:/producto/ssd";
                break;
            case "hhd":
                ruta ="redirect:/producto/hhd";
                break;
            case "tmadre":
                ruta = "redirect:/producto/tarjeta-madre";
                break;
            case "tgrafica":
                ruta = "redirect:/producto/tarjeta-grafica";
                break;
            case "disipador":
                ruta = "redirect:/producto/disipador";
                break;
            case "fpoder":
                ruta = "redirect:/producto/fuente-poder";
                break;
            case "case":
                ruta = "redirect:/producto/case";
                break;
        }

        productoService.delete(id);

        return ruta;

    }
}
