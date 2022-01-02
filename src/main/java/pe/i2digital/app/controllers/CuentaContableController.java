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
    @GetMapping("/numero/{numero}")
    public ResponseEntity<?> devolverPorNumero(@PathVariable String numero) {
        return ResponseEntity.ok(service.findByNumeroStartingWithOrderByIdAsc(numero));
    }
    @GetMapping("/filtro-numero/1/{numero}")
    public ResponseEntity<?> busquedaPersonalizadaNumero(@PathVariable String numero) {
        System.out.println("Filtro 1");
        return ResponseEntity.ok(service.busquedaPersonalizadaNumero(numero));
    }
    @GetMapping("/filtro-numero/2/{numero}")
    public ResponseEntity<?> busquedaPersonalista(@PathVariable String numero) {
        System.out.println("Filtro 2");
        return ResponseEntity.ok(service.findByNumeroStartingWithAndUsaDocumentoTrueOrderById(numero));
    }
    @GetMapping("/filtro-numero/3/{numero}")
    public ResponseEntity<?> busquedaNumeroOperacionTesoreria(@PathVariable String numero) {
        System.out.println("Filtro 3");
        return ResponseEntity.ok(service.busquedaNumeroOperacionTesoreria(numero));
    }
}
