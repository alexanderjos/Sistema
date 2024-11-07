package com.Valverde.sistema.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Valverde.sistema.entity.DetalleIngreso;

import java.util.List;

@Repository
public interface DetalleIngresoRepository extends JpaRepository<DetalleIngreso, Integer> {
    List<DetalleIngreso> findByCantidad(short cantidad, Pageable pageable);
    short countByIngresoId(int ingresoId);
    short countByProductoId(int productoId);


}
