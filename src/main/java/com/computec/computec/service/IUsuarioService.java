package com.computec.computec.service;

import com.computec.computec.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {
    Optional<Usuario> findById(Integer id);
    Usuario save (Usuario usuario);
    Optional<Usuario> findByCorreo(String correo);
    void registrarUsuario(Usuario usuario);
    boolean checkPassword(String password, String encodedPassword);
    String encriptarContra(String contra);
}
