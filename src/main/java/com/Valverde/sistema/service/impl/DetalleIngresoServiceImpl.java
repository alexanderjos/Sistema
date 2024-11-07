package com.Valverde.sistema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Valverde.sistema.entity.DetalleIngreso;
import com.Valverde.sistema.entity.Ingreso;
import com.Valverde.sistema.entity.Producto;
import com.Valverde.sistema.exception.GeneralException;
import com.Valverde.sistema.exception.NoDataFoundException;
import com.Valverde.sistema.exception.ValidateException;
import com.Valverde.sistema.repository.DetalleIngresoRepository;

import com.Valverde.sistema.service.DetalleIngresoService;
import com.Valverde.sistema.service.IngresoService;
import com.Valverde.sistema.service.ProductoService;

@Service

public class DetalleIngresoServiceImpl implements DetalleIngresoService {
    @Autowired
    private DetalleIngresoRepository repository;
    @Autowired
    private ProductoService productoService;

    @Autowired
    private IngresoService ingresoService;
    
    @Override
    @Transactional(readOnly = true)

    public List<DetalleIngreso> findAll(Pageable page) {
        try {
            List<DetalleIngreso> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    public List<DetalleIngreso> findAll() {
        try {
            List<DetalleIngreso> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    @Transactional(readOnly = true)

    public List<DetalleIngreso> findByCantidad(short cantidad, Pageable page) {
        try {
            List<DetalleIngreso> registros = repository.findByCantidad(cantidad, page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public DetalleIngreso findById(int id) {
        try {
            DetalleIngreso registro = repository.findById(id)
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
public DetalleIngreso save(DetalleIngreso detalleIngreso) {
    try {
        // Verificar si el producto existe
        if (detalleIngreso.getProducto() != null) {
            Producto producto = productoService.findById(detalleIngreso.getProducto().getId());

            // Aquí es donde estableces el tipo de documento en el cliente
            detalleIngreso.setProducto(producto);            
        } else {
            throw new ValidateException("La categoria del producto es requerido");
        }

        // Verificar si el ingreso existe
        if (detalleIngreso.getIngreso() != null) {
            Ingreso ingreso = ingresoService.findById(detalleIngreso.getIngreso().getId());

            // Aquí es donde estableces el tipo de documento en el cliente
            detalleIngreso.setIngreso(ingreso);            
        } else {
            throw new ValidateException("La categoria del producto es requerido");
        }

        // Validar el cliente (ya ahora con el tipo de documento cargado)
        //ClienteValidator.save(cliente);

        // Guardar o actualizar el cliente
        if (detalleIngreso.getId() == 0) {
            return repository.save(detalleIngreso); // Guardar nuevo cliente
        } else {
            DetalleIngreso registro = repository.findById(detalleIngreso.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            registro.setCantidad(detalleIngreso.getCantidad());
            registro.setPrecioUnitario(detalleIngreso.getPrecioUnitario());
            registro.setProducto(detalleIngreso.getProducto());
            registro.setIngreso(detalleIngreso.getIngreso());
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
            DetalleIngreso registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    
    
}
