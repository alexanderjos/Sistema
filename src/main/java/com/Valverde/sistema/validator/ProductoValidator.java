package com.Valverde.sistema.validator;

import com.Valverde.sistema.entity.Producto;
import com.Valverde.sistema.exception.ValidateException;

public class ProductoValidator {
    public static void save(Producto registro) {
        // Validación del nombre
        if (registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }
        if (registro.getNombre().length() > 100) {
            throw new ValidateException("El nombre no debe exceder los 70 caracteres");
        }
        if (registro.getDescripcion().length() >255) {
            throw new ValidateException("La descripción no puede excerde de los 255 caracteres");
        }
        if (registro.getDescripcion() ==  null || registro.getDescripcion().trim().isEmpty()) {
            throw new ValidateException("La descripción es requerido");
        }
        
        
    }
}
