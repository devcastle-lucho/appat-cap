/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class HTTPUtils {
    public static ResponseEntity<?> validar(BindingResult result) {
        Map<String,Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(fe -> errores.put(fe.getField(), "El campo '"+fe.getField()+"': "+fe.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
