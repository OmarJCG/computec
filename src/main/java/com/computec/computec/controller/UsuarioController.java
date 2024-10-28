package com.computec.computec.controller;


import com.computec.computec.model.Contra;
import com.computec.computec.model.DetalleCompra;
import com.computec.computec.model.Compra;
import com.computec.computec.model.Usuario;
import com.computec.computec.service.ICompraService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger LOGGER= LoggerFactory.getLogger(UsuarioController.class);



    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICompraService ordenService;

    @Autowired
    private HomeController homeController;

    @GetMapping("/registro")
    public String create(){
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {

        //Logback
        LOGGER.info("Usuario del formulario registro: {}", usuario);

        usuarioService.registrarUsuario(usuario);

        return "redirect:/usuario/login";
    }

    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session, RedirectAttributes redirectAttributes){
        LOGGER.info("Usuario formulario login: {}", usuario);

        Optional<Usuario> user = usuarioService.findByCorreo(usuario.getCorreo());

        boolean coincideContra = usuarioService.checkPassword(usuario.getPassword(), user.get().getPassword());


        if(user.isPresent() && coincideContra){
            session.setAttribute("usuario", user.get());

            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else {
                return "redirect:/";
            }
        }else{
            LOGGER.info("usuario no existe");
        }

        String error = "Los datos ingresados son incorrectos";
        redirectAttributes.addFlashAttribute("error", error);

        return "redirect:/usuario/login";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion( HttpSession session ) {

        homeController.setDetalles(new ArrayList<DetalleCompra>());
        homeController.setOrden(new Compra());
        session.removeAttribute("usuario");


        return "redirect:/";
    }

    @GetMapping("/info")
    private String info(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "/usuario/info";
    }

    @GetMapping("/show-contra")
    public String showContra(){
        return "/usuario/contra";
    }

    @PostMapping("/update-contra")
    public String updateContra(Contra contra, HttpSession session, RedirectAttributes redirectAttributes){

        //Logback
        LOGGER.info("Contraseña del formulario: {}", contra);

        Usuario usuario = (Usuario) session.getAttribute("usuario");


        if(usuarioService.checkPassword(contra.getOldContra(), usuario.getPassword())){
            usuario.setPassword(usuarioService.encriptarContra(contra.getNewContra()));
            usuarioService.save(usuario);
            return  "redirect:/usuario/info";

        }

        String error = "La contraseña anterior es incorrecta. Por favor, inténtalo nuevamente.";
        redirectAttributes.addFlashAttribute("error", error);

        return  "redirect:/usuario/show-contra";
    }

    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("usuario", session.getAttribute("usuario"));

        Usuario usuario= (Usuario) session.getAttribute("usuario");

        List<Compra> ordenes= ordenService.findByUsuario(usuario);
        LOGGER.info("ordenes {}", ordenes);

        model.addAttribute("ordenes", ordenes);

        return "usuario/compra";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        LOGGER.info("Id de la orden: {}", id);
        Optional<Compra> orden=ordenService.findById(id);

        model.addAttribute("detalles", orden.get().getDetalle());


        //session
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("id", id);
        return "usuario/detallecompra";
    }
}
