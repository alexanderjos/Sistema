package com.Valverde.sistema.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Valverde.sistema.entity.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    List<Permiso> findByNombreContaining(String nombre, Pageable pageable);
}
