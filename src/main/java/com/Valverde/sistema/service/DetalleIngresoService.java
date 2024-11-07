package com.Valverde.sistema.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Valverde.sistema.entity.DetalleIngreso;


public interface DetalleIngresoService {
    public List<DetalleIngreso> findAll(Pageable page);
    public List<DetalleIngreso> findAll();
    public List<DetalleIngreso> findByCantidad(short cantidad, Pageable page);
    public DetalleIngreso findById(int id);
    public DetalleIngreso save(DetalleIngreso detalleIngreso);
    public void delete(int id);
}
