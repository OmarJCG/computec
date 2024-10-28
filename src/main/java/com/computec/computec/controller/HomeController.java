package com.computec.computec.controller;

import com.computec.computec.model.*;
import com.computec.computec.service.IDetalleCompraService;
import com.computec.computec.service.IDetalleProductoService;
import com.computec.computec.service.ICompraService;
import com.computec.computec.service.IProductoService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IDetalleProductoService detalleProductoService;

    @Autowired
    private ICompraService ordenService;

    @Autowired
    private IDetalleCompraService detalleOrdenService;


    //Para almacenar detalle de la orden
    List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();

    //datos de la orden
    Compra orden = new Compra();

    DecimalFormat df = new DecimalFormat("#.00");


    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }

    public void setOrden(Compra orden) {
        this.orden = orden;
    }

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
                // Google Guava
                atributosDetalle = ImmutableList.of("Núcleos", "Hilos", "Frecuencia base", "Frecuencia turbo máxima", "Cache",
                        "Tipo de memoria", "TDP/TDP predeterminado", "Memoria máxima",
                        "Gráficos integrados", "Overclocking", "Versión de PCI Express");
                break;
            case "ram":
                // Google Guava
                atributosDetalle = ImmutableList.of("Capacidad", "Velocidad", "Tipo de memoria", "Latencia CAS",
                        "Voltaje", "Latencia SPD", "Factor de forma", "Formato", "Temperatura de operación",
                        "Frecuencia máxima", "RGB");
                break;
            case "ssd":
                // Google Guava
                atributosDetalle = ImmutableList.of("Capacidad", "Interfaz", "Velocidad de lectura", "Velocidad de escritura",
                        "Tipo de memoria NAND", "Durabilidad (TBW)", "Consumo de energía",
                        "Factor de forma", "Encriptación", "MTBF", "Garantía");
                break;
            case "hhd":
                // Google Guava
                atributosDetalle = ImmutableList.of("Capacidad", "Velocidad de rotación", "Interfaz", "Caché",
                        "Formato", "Tamaño físico", "Consumo de energía", "Durabilidad",
                        "Tiempo medio entre fallos (MTBF)", "Tecnología de grabación", "Ruido");
                break;
            case "tmadre":
                // Google Guava
                atributosDetalle = ImmutableList.of("Socket", "Chipset", "Factores de forma", "Número de slots de RAM",
                        "Número de puertos USB", "Puertos SATA", "Compatibilidad con PCIe",
                        "Audio integrado", "Red integrada", "Capacidad de overclocking", "BIOS/UEFI");
                break;
            case "tgrafica":
                // Google Guava
                atributosDetalle = ImmutableList.of("VRAM", "Tipo de memoria", "Frecuencia del núcleo", "Velocidad de la memoria",
                        "Núcleos CUDA/Stream processors", "Interfaz", "TDP", "Salidas de video",
                        "Soporte para ray tracing", "Overclocking", "Dimensiones");
                break;
            case "disipador":
                // Google Guava
                atributosDetalle = ImmutableList.of("Tipo (aire/líquido)", "Dimensiones", "Compatibilidad", "Rendimiento térmico",
                        "Nivel de ruido", "Ventiladores incluidos", "Material del radiador",
                        "Base de contacto", "RGB", "Facilidad de instalación", "Peso");
                break;
            case "fpoder":
                // Google Guava
                atributosDetalle = ImmutableList.of("Potencia total (W)", "Certificación (80 Plus)", "Número de rieles",
                        "Conectores disponibles", "Tipo de ventilador", "Tamaño físico",
                        "Protecciones (OVP, OCP, SCP)", "Modularidad", "Eficiencia",
                        "Ruido", "MTBF");
                break;
            case "case":
                // Google Guava
                atributosDetalle = ImmutableList.of("Tipo de caja (ATX, microATX, etc.)", "Dimensiones", "Soporte para refrigeración",
                        "Espacio para GPU", "Número de bahías para discos", "Materiales",
                        "Ventiladores incluidos", "Puertos frontales", "Gestión de cables",
                        "Filtro de polvo", "RGB");
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


    @PostMapping("/cart")
    public String addCarrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model model, HttpSession session){

        model.addAttribute("usuario", session.getAttribute("usuario"));

        DetalleCompra detalleOrden  = new DetalleCompra();
        Producto producto = new Producto();
        BigDecimal sumaTotal;

        Optional<Producto> optionalProducto = productoService.get(id);

        LOGGER.info("producto para carrito: {}", optionalProducto.get());
        LOGGER.info("cantidad: {}", cantidad);

        producto = optionalProducto.get();


        DetalleCompra dcopia = new DetalleCompra();
        for (DetalleCompra d : detalles){
            if(d.getProducto().getId().equals(producto.getId())){
                dcopia = d;
            }
        }
        
        detalles.remove(dcopia);


        BigDecimal precio = producto.getPrecio();
        BigDecimal total = producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));

        String nombre = producto.getMarca() + " "+ producto.getModelo();

        detalleOrden.setNombre(producto.getMarca() + " "+ producto.getModelo());
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(precio);
        detalleOrden.setTotal(total);
        detalleOrden.setProducto(producto);
        detalleOrden.setImg(producto.getImg());



        detalles.add(detalleOrden);


        sumaTotal =detalles.stream()
                .map(DetalleCompra::getTotal) // Convierte cada `DetalleCompra` a su total (BigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Suma todos los totales

        int sumaCantidad = detalles.stream().mapToInt(dt -> dt.getCantidad()).sum();



        orden.setTotal(sumaTotal);

        model.addAttribute("p", producto);
        model.addAttribute("cart", detalles);
        model.addAttribute("sumacantidad", sumaCantidad);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    // quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model, HttpSession session) {

        model.addAttribute("usuario", session.getAttribute("usuario"));

        // lista nueva de prodcutos
        List<DetalleCompra> ordenesNueva = new ArrayList<DetalleCompra>();

        for (DetalleCompra detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) {
                ordenesNueva.add(detalleOrden);
            }
        }

        // poner la nueva lista con los productos restantes
        detalles = ordenesNueva;

        BigDecimal sumaTotal;

        sumaTotal =detalles.stream()
                .map(DetalleCompra::getTotal) // Convierte cada `DetalleCompra` a su total (BigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Suma todos los totales

        int sumaCantidad = detalles.stream().mapToInt(dt -> dt.getCantidad()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("sumacantidad", sumaCantidad);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {

        model.addAttribute("usuario", session.getAttribute("usuario"));

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        int sumaCantidad = detalles.stream().mapToInt(dt -> dt.getCantidad()).sum();
        model.addAttribute("sumacantidad", sumaCantidad);

        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", session.getAttribute("usuario"));

        return "usuario/detalleorden";
    }

    // guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session ) {

        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //usuario
        orden.setUsuario((Usuario) session.getAttribute("usuario"));
        ordenService.save(orden);

        //guardar detalles
        for (DetalleCompra dt:detalles) {
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        ///limpiar lista y orden
        orden = new Compra();
        detalles.clear();

        return "redirect:/";
    }

    @GetMapping("/confimarcompra")
    public String confimarCompra(Model model, HttpSession session){

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario==null){
            return "redirect:/usuario/login";
        }


        model.addAttribute("usuario", session.getAttribute("usuario"));

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        int sumaCantidad = detalles.stream().mapToInt(dt -> dt.getCantidad()).sum();
        model.addAttribute("sumacantidad", sumaCantidad);

        String confirmarshow = "confirmarshow";
        String noscroll = "noscroll";

        model.addAttribute("confirmarshow", confirmarshow);
        model.addAttribute("noscroll", noscroll);

        return "usuario/carrito";
    }

}
