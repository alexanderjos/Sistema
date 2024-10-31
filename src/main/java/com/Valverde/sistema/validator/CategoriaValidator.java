package com.Valverde.sistema.validator;

import com.Valverde.sistema.entity.Categoria;
import com.Valverde.sistema.exception.ValidateException;

public class CategoriaValidator {
    public static void save(Categoria registro) {
        if(registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }
        if(registro.getNombre().length() > 70) {
            throw new ValidateException("El nombre no debe exceder los 70 caracteres");
        }
    }
}
