package com.Valverde.sistema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Valverde.sistema.entity.Ingreso;
import com.Valverde.sistema.entity.Usuario;
import com.Valverde.sistema.exception.GeneralException;
import com.Valverde.sistema.exception.NoDataFoundException;
import com.Valverde.sistema.exception.ValidateException;
import com.Valverde.sistema.repository.IngresoRepository;

import com.Valverde.sistema.service.IngresoService;
import com.Valverde.sistema.service.UsuarioService;

@Service

public class IngresoServiceImp implements IngresoService {
    @Autowired
    private IngresoRepository repository;
    @Autowired
    private UsuarioService usuarioService;
    
    @Override
    @Transactional(readOnly = true)

    public List<Ingreso> findAll(Pageable page) {
        try {
            List<Ingreso> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    public List<Ingreso> findAll() {
        try {
            List<Ingreso> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    @Transactional(readOnly = true)

    public List<Ingreso> findBySerie(String serie, Pageable page) {
        try {
            List<Ingreso> registros = repository.findBySerie(serie, page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public Ingreso findById(int id) {
        try {
            Ingreso registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
@Transactional
public Ingreso save(Ingreso ingreso) {
    try {
        // Verificar si el tipo de documento existe
        if (ingreso.getUsuario() != null) {
            Usuario usuario = usuarioService.findById(ingreso.getUsuario().getId());
            ingreso.setUsuario(usuario);
        } else {
            throw new ValidateException("La categoria del producto es requerido");
        }

        // Validar el cliente (ya ahora con el tipo de documento cargado)
        //ClienteValidator.save(cliente);

        // Guardar o actualizar el cliente
        if (ingreso.getId() == 0) {
            return repository.save(ingreso); // Guardar nuevo cliente
        } else {
            Ingreso registro = repository.findById(ingreso.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            registro.setSerie(ingreso.getSerie());
            registro.setNumero(ingreso.getNumero());
            registro.setFecha(ingreso.getFecha());
            registro.setTotal(ingreso.getTotal());
            registro.setUsuario(ingreso.getUsuario());
            return repository.save(registro); // Guardar cliente actualizado
        }
    } catch (ValidateException | NoDataFoundException e) {
        throw e; // Re-lanzar excepciones de validación
    } catch (GeneralException e) {
        throw new GeneralException("Error del servidor");
    }
}

    @Override
    public void delete(int id) {
        try {
            Ingreso registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    
    
}
