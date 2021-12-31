package pe.i2digital.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.services.CuentaContableService;

@RequestMapping("/cuentacontable")
@RestController(value="cuentacontable")
public class CuentaContableController {
    @Autowired
    private CuentaContableService service;
    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> devolver(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
