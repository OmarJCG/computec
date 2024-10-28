package com.computec.computec.service.impl;

import com.computec.computec.dao.ICompraDao;
import com.computec.computec.model.Compra;
import com.computec.computec.model.Usuario;
import com.computec.computec.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private ICompraDao ordenDao;

    @Override
    public List<Compra> findAll() {
        return ordenDao.findAll();
    }

    @Override
    public Optional<Compra> findById(Integer id) {
        return ordenDao.findById(id);
    }

    @Override
    public Compra save(Compra orden) {

        return ordenDao.save(orden);
    }


    @Override
    public List<Compra> findByUsuario(Usuario usuario) {

        return ordenDao.findByUsuario(usuario);
    }

    // 0000010
    @Override
    public String generarNumeroOrden() {
        int numero=0;
        String numeroConcatenado="";

        List<Compra> ordenes = findAll();

        List<Integer> numeros= new ArrayList<Integer>();

        ordenes.stream().forEach(o -> numeros.add( Integer.parseInt( o.getNumero())));

        if (ordenes.isEmpty()) {
            numero=1;
        }else {
            numero= numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero<10) { //0000001000
            numeroConcatenado="000000000"+String.valueOf(numero);
        }else if(numero<100) {
            numeroConcatenado="00000000"+String.valueOf(numero);
        }else if(numero<1000) {
            numeroConcatenado="0000000"+String.valueOf(numero);
        }else if(numero<10000) {
            numeroConcatenado="0000000"+String.valueOf(numero);
        }

        return numeroConcatenado;
    }
}
