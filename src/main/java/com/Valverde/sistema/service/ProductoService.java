package com.Valverde.sistema.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Valverde.sistema.entity.Producto;


public interface ProductoService {
    public List<Producto> findAll(Pageable page);
    public List<Producto> findAll();
    public List<Producto> findByNombre(String nombre, Pageable page);
    public Producto findById(int id);
    public Producto save(Producto producto);
    public void delete(int id);
}
