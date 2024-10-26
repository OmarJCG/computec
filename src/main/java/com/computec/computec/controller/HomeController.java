package com.computec.computec.controller;

import com.computec.computec.model.DetalleOrden;
import com.computec.computec.model.DetalleProducto;
import com.computec.computec.model.Orden;
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
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

    //Para almacenar detalle de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //datos de la orden
    Orden orden = new Orden();

    DecimalFormat df = new DecimalFormat("#.00");

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

        DetalleOrden detalleOrden  = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(id);

        LOGGER.info("producto para carrito: {}", optionalProducto.get());
        LOGGER.info("cantidad: {}", cantidad);

        producto = optionalProducto.get();


        double precio = Double.valueOf(df.format(producto.getPrecio()));
        double total = Double.valueOf(df.format(producto.getPrecio()*cantidad));

        detalleOrden.setNombre(producto.getMarca() + " "+ producto.getModelo());
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio( precio);
        detalleOrden.setTotal(total);
        detalleOrden.setProducto(producto);
        detalleOrden.setImg(producto.getImg());

        //validar que le producto no se añada 2 veces
        Integer idProducto=producto.getId();
        boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }


        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        int sumaCantidad = detalles.stream().mapToInt(dt -> dt.getCantidad()).sum();



        orden.setTotal(Double.valueOf(df.format(sumaTotal)));

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
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) {
                ordenesNueva.add(detalleOrden);
            }
        }

        // poner la nueva lista con los productos restantes
        detalles = ordenesNueva;

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        int sumaCantidad = detalles.stream().mapToInt(dt -> dt.getCantidad()).sum();

        orden.setTotal(Double.valueOf(df.format(sumaTotal)));
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

}
