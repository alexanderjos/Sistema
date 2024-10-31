package com.Valverde.sistema.service;

import org.springframework.data.domain.Pageable;

import com.Valverde.sistema.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    public List<Categoria> findAll(Pageable page);
    public List<Categoria> findAll();
    public List<Categoria> findByNombre(String nombre, Pageable page);
    public Categoria findById(int id);
    public Categoria save(Categoria categoria);
    public void delete(int id);
}