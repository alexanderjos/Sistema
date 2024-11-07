package com.Valverde.sistema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Valverde.sistema.entity.Categoria;
import com.Valverde.sistema.entity.Producto;
import com.Valverde.sistema.exception.GeneralException;
import com.Valverde.sistema.exception.NoDataFoundException;
import com.Valverde.sistema.exception.ValidateException;
import com.Valverde.sistema.repository.ProductoRepository;
import com.Valverde.sistema.service.CategoriaService;
import com.Valverde.sistema.service.ProductoService;

@Service

public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository repository;
    @Autowired
    private CategoriaService categoriaService;
    
    @Override
    @Transactional(readOnly = true)

    public List<Producto> findAll(Pageable page) {
        try {
            List<Producto> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    public List<Producto> findAll() {
        try {
            List<Producto> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    @Transactional(readOnly = true)

    public List<Producto> findByNombre(String nombre, Pageable page) {
        try {
            List<Producto> registros = repository.findByNombre(nombre, page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public Producto findById(int id) {
        try {
            Producto registro = repository.findById(id)
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
public Producto save(Producto producto) {
    try {
        // Verificar si el tipo de documento existe
        if (producto.getCategoria() != null) {
            Categoria categoria = categoriaService.findById(producto.getCategoria().getId());

            // Aquí es donde estableces el tipo de documento en el cliente
            
            producto.setCategoria(categoria);
        } else {
            throw new ValidateException("La categoria del producto es requerido");
        }

        // Validar el cliente (ya ahora con el tipo de documento cargado)
        //ClienteValidator.save(cliente);

        // Guardar o actualizar el cliente
        if (producto.getId() == 0) {
            return repository.save(producto); // Guardar nuevo cliente
        } else {
            Producto registro = repository.findById(producto.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            registro.setNombre(producto.getNombre());
            registro.setDescripcion(producto.getDescripcion());
            registro.setActivo(producto.isActivo());
            registro.setCategoria(producto.getCategoria());
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
            Producto registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    
    
}
