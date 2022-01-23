package pe.i2digital.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.services.CentroCostosService;
import java.util.logging.*;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.validation.BindingResult;
import pe.i2digital.app.utils.HTTPUtils;

@RequestMapping("/api/v1/centrocostos")
@RestController(value="centrocostos")
@Log //jul
public class CentroCostosController {
    private static final Logger logger = Logger.getLogger(CentroCostosController.class.getName());
    
    @Autowired
    private CentroCostosService service;

    //Leer: GET -> Tener en cuenta RMM: Modelo Richardson
    @GetMapping("/")
    public ResponseEntity<?> listar() {
        //var a=1/0; ArithmeticException
        logger.log(Level.INFO, "Antes de llamar al servicio");
        return ResponseEntity.ok(service.findAll());//Respuesta 200
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> devolver(@PathVariable Integer id) {        
        var objeto = service.findById(id);
        if(Objects.nonNull(objeto))
            return ResponseEntity.ok(objeto);//Respuesta 200
        else {
            log.log(Level.WARNING, "No se encuentra centro costos - ID={0}", id);
            return ResponseEntity.notFound().build();
        }                
    }
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CentroCostos o/*, BindingResult result*/
    ) {
        /*if(result.hasErrors()) {
            var r = HTTPUtils.validar(result);
            log.log(Level.WARNING, r.getBody().toString());
            return r;
        }*/
            
        var oDB =service.save(o);
        return ResponseEntity.status(HttpStatus.CREATED).body(oDB);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody CentroCostos o, BindingResult result, @PathVariable Integer id) { //No centralizado: BindingResult
        if(result.hasErrors()) //Mapeo de errores dentro de peticion API
            return HTTPUtils.validar(result);
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
