package com.computec.computec.service.impl;

import com.computec.computec.model.Usuario;
import com.computec.computec.dao.IUsuarioDao;
import com.computec.computec.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioDao usuarioRepository;

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());

        usuario.setTipo("USER");
        usuario.setPassword(encodedPassword);

        usuarioRepository.save(usuario);
    }

    @Override
    public boolean checkPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    @Override
    public String encriptarContra(String contra) {

        String encodedPassword = passwordEncoder.encode(contra);

        return encodedPassword;
    }


}
