/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.i2digital.app.services.SucursalService;
import pe.i2digital.app.services.UsuarioService;

@RequestMapping("/api/v1/usuario")
@RestController(value="usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    
    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> devolver(@PathVariable String codigo) throws Exception {
        return ResponseEntity.ok(service.findByCodigo(codigo));
    }
}
