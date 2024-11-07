package com.Valverde.sistema.validator;

import com.Valverde.sistema.entity.Ingreso;
import com.Valverde.sistema.exception.ValidateException;

public class IngresoValidator {
    public static void save(Ingreso registro) {
        // Validación del nombre
       if (registro.getSerie()== null || registro.getSerie().trim().isEmpty()) {
            throw new ValidateException("La serie es requerida");
       }
        if (registro.getSerie().length() > 64) {
            throw new ValidateException("La serie no debe exceder los 64 caracteres");
        }
        if (registro.getFecha() == null) {
            throw new ValidateException("La fecha es requerida");
        }
        if(registro.getNumero() == null || registro.getNumero().trim().isEmpty()){
            throw new ValidateException("El número es requerido");
        }
        if (registro.getNumero().length() > 100) {
            throw new ValidateException("El número no debe exceder los 64 caracteres");
            
        }
        if (registro.getTotal()<0) {
            throw new ValidateException("El total es requerido");
            
        }
    }
}
