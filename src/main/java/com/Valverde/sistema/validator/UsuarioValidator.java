package com.Valverde.sistema.validator;

import com.Valverde.sistema.entity.Usuario;
import com.Valverde.sistema.exception.ValidateException;

public class UsuarioValidator {
    public static void save(Usuario registro) {
        // Validación del nombre
        if (registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }
        if (registro.getNombre().length() > 100) {
            throw new ValidateException("El nombre no debe exceder los 70 caracteres");
        }
        if (registro.getEmail() == null || registro.getEmail().trim().isEmpty()) {
            throw new ValidateException("El email es requerido");
        }
        if (registro.getEmail().length() > 100) {
            throw new ValidateException("El email no debe exceder los 100 caracteres");
            
        }
        if (registro.getContrasena() == null || registro.getContrasena().trim().isEmpty()) {
            throw new ValidateException("La contraseña es requerida");
        }
        
    }
}
