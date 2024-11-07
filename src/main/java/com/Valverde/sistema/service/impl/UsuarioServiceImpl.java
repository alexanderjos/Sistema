package com.Valverde.sistema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Valverde.sistema.entity.Rol;
import com.Valverde.sistema.entity.Usuario;
import com.Valverde.sistema.exception.GeneralException;
import com.Valverde.sistema.exception.NoDataFoundException;
import com.Valverde.sistema.exception.ValidateException;
import com.Valverde.sistema.repository.UsuarioRepository;
import com.Valverde.sistema.service.RolService;
import com.Valverde.sistema.service.UsuarioService;

@Service

public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private RolService rolService;
    
    @Override
    @Transactional(readOnly = true)

    public List<Usuario> findAll(Pageable page) {
        try {
            List<Usuario> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    public List<Usuario> findAll() {
        try {
            List<Usuario> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    @Transactional(readOnly = true)

    public List<Usuario> findByNombre(String nombre, Pageable page) {
        try {
            List<Usuario> registros = repository.findByNombre(nombre, page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public Usuario findById(int id) {
        try {
            Usuario registro = repository.findById(id)
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
public Usuario save(Usuario usuario) {
    try {
        // Verificar si el tipo de documento existe
        if (usuario.getRol() != null) {
            Rol rol = rolService.findById(usuario.getRol().getId());

            // Aquí es donde estableces el tipo de documento en el cliente
            
            usuario.setRol(rol);
        } else {
            throw new ValidateException("El rol del usuario es requerido");
        }

        // Validar el cliente (ya ahora con el tipo de documento cargado)
        //ClienteValidator.save(cliente);

        // Guardar o actualizar el cliente
        if (usuario.getId() == 0) {
            return repository.save(usuario); // Guardar nuevo cliente
        } else {
            Usuario registro = repository.findById(usuario.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            registro.setNombre(usuario.getNombre());
            registro.setContrasena(usuario.getContrasena());
            registro.setEmail(usuario.getEmail());
            registro.setActivo(usuario.isActivo());
            registro.setRol(usuario.getRol());
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
            Usuario registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    
    
}
