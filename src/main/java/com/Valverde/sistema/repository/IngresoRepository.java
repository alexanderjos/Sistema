package com.Valverde.sistema.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Valverde.sistema.entity.Ingreso;

import java.util.List;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Integer> {
    List<Ingreso> findBySerie(String serie, Pageable pageable);
    short countByUsuarioId(int usuarioId);

}
