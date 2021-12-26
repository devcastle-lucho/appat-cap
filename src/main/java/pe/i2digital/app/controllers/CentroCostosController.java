package pe.i2digital.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.services.CentroCostosService;

import java.util.Objects;

@RequestMapping("/centrocostos")
@RestController(value="centrocostos")
public class CentroCostosController {
    @Autowired
    private CentroCostosService service;

    //Leer: GET -> Tener en cuenta RMM: Modelo Richardson
    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());//Respuesta 200
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> devolver(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));//Respuesta 200
    }
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CentroCostos o) {
        var oDB =service.save(o);
        return ResponseEntity.status(HttpStatus.CREATED).body(oDB);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody CentroCostos o,@PathVariable Integer id) {
        var oDB = service.findById(id);
        if(Objects.isNull(o))
            return ResponseEntity.notFound().build();
        oDB.setNombre(o.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(oDB));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
