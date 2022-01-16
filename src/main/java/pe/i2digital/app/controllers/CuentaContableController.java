package pe.i2digital.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.request.CuentaContableRequest;
import pe.i2digital.app.services.CuentaContableService;

@Api(tags = "API - Cuenta contable",value = "API CUENTA CONTABLE", description = "Se encarga de procesos/mantenimiento de la tabla maestra cuenta contable")
@RequestMapping("/cuentacontable")
@RestController(value="cuentacontable")
public class CuentaContableController {
    @Autowired
    private CuentaContableService service;
    
    @ApiOperation(value = "Lista todos los registros de la cuenta contable", notes = "-Todos atributos de cuenta contable")    
    @RequestMapping(value = "/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CuentaContable> listar() {
        return (List<CuentaContable>) service.findAll();
    }
    /*
    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }*/
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
    @PutMapping("/{ruc}/iud/{accion}")
    public ResponseEntity<?> iudJson(@PathVariable String ruc, @PathVariable String accion,@RequestBody CuentaContableRequest request) throws Exception {
        //System.out.println(request.getOCuentaContable());
        System.out.println("objeto: "+ new ObjectMapper().writeValueAsString(request.getOCuentaContable()));
        return ResponseEntity.ok(service.iudJson(ruc,accion,request));
    }
}
