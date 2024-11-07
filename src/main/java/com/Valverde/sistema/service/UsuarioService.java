package com.Valverde.sistema.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Valverde.sistema.entity.Usuario;


public interface UsuarioService {
    public List<Usuario> findAll(Pageable page);
    public List<Usuario> findAll();
    public List<Usuario> findByNombre(String nombre, Pageable page);
    public Usuario findById(int id);
    public Usuario save(Usuario usuario);
    public void delete(int id);
}
