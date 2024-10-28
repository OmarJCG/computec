package com.computec.computec.controller;

import com.computec.computec.model.DetalleProducto;
import com.computec.computec.model.Producto;
import com.computec.computec.service.IDetalleProductoService;
import com.computec.computec.service.IProductoService;
import com.computec.computec.service.IUsuarioService;
import com.computec.computec.service.impl.UploadFileSerivice;
import com.google.common.collect.ImmutableList;
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


        DetalleProducto dp =  detalleProductoService.save(detalleProducto);

        producto.setDetalleProducto(dp);

        productoService.guardarProductoConImagen(producto, file);

        String ruta = "redirect:/producto/show/"+productosPorCategoria.get(producto.getCategoria());

        return ruta;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, HttpSession session){

        model.addAttribute("usuario", session.getAttribute("usuario"));

        Optional<Producto> optionalProducto = productoService.get(id);

        Producto producto = optionalProducto.get();

        DetalleProducto detalleProducto = producto.getDetalleProducto();

        //Logback
        LOGGER.info("producto de la base de datos : {}", producto);


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

        model.addAttribute("categoria", productosPorCategoria.get(categoria));

        model.addAttribute("p", producto);
        model.addAttribute("dp", detalleProducto);
        model.addAttribute("ad", atributosDetalle);

        return "producto/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, DetalleProducto detalleProducto, @RequestParam("image") MultipartFile file) throws IOException {

        //Logback
        LOGGER.info("producto formulario modificar: {}", producto);
        LOGGER.info("detalle producto formulario modificar: {}", detalleProducto);

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




        producto.setCategoria(productosPorCategoria.get(producto.getCategoria()));
        ruta = "redirect:/producto/show/"+producto.getCategoria();


        detalleProductoService.update(detalleProducto);

        Optional<DetalleProducto> dp= detalleProductoService.get(detalleProducto.getIdDetalleProducto());

        producto.setDetalleProducto(dp.get());

        productoService.actualizarProductoConImagen(p.getId(), producto, file);

        return ruta;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) throws IOException {

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

        DetalleProducto detalleProducto = p.getDetalleProducto();

        productoService.eliminarImagenProducto(id);

        detalleProductoService.delete(detalleProducto.getIdDetalleProducto());


        return "redirect:/producto/show/"+productosPorCategoria.get(producto.get().getCategoria());

    }
}
