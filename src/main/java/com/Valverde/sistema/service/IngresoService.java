package com.Valverde.sistema.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Valverde.sistema.entity.Ingreso;


public interface IngresoService {
    public List<Ingreso> findAll(Pageable page);
    public List<Ingreso> findAll();
    public List<Ingreso> findBySerie(String serie, Pageable page);
    public Ingreso findById(int id);
    public Ingreso save(Ingreso ingreso);
    public void delete(int id);
}
