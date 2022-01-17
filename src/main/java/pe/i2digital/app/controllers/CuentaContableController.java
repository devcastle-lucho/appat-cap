package pe.i2digital.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.dto.projection.CuentaContableCustom;
import pe.i2digital.app.models.dto.projection.CuentaContableVista;
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
    //Con anotacion @io.swagger.v3.oas
    @Operation(summary = "Devuelve la cuenta contable a partir de un identificador ")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Devuelve el registro de la cuenta contable",
                    content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CuentaContable.class)) 
            ),
            @ApiResponse(responseCode = "400", description = "Invalido",content =@Content),
            @ApiResponse(responseCode = "404", description = "No se encuentra la cuenta contable",content =@Content)
        }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public ResponseEntity<?> devolver(
            @ApiParam(value="Identificador de la cuenta contable",example = "1")//No se puede leer el valor x defecto
            @PathVariable Integer id) {
        var oCuentaContable = service.findById(id);
        if(Objects.nonNull(oCuentaContable))
            return ResponseEntity.ok(oCuentaContable);
        else return ResponseEntity.notFound().build();
    }
    
    //Con anotacion @io.swagger.v3.oas
    @Operation(summary = "Listar todos los registros de la cuenta contable a partir de un numero.", operationId = "GET_LISTAR_1", 
            description = "- Se lista con el filtro parte del número inicial y se ordenar ascendentemente.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Devuelve el registro de la cuenta contable",
                    content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
                            array = @ArraySchema(schema = @Schema(implementation = CuentaContableDTO.class)))                      
            ),
            @ApiResponse(responseCode = "400", description = "Invalido",content =@Content),
            @ApiResponse(responseCode = "404", description = "No se encuentra  registros de cuenta contable",content =@Content)
        }
    )
    @GetMapping(value ="/numero/{numero}", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> devolverPorNumero(
            @Parameter(description = "Parte del numero inicial",example = "10")
            @PathVariable String numero) {
        var lista =service.findByNumeroStartingWithOrderByIdAsc(numero);
        if(Objects.nonNull(lista) && lista.size()>0)
            return ResponseEntity.ok(lista);
        else return ResponseEntity.notFound().build();
    }
    //io.swagger
    @ApiOperation(value = "Listar todos los registros de la cuenta contable a partir de un numero (filtro tipo 1).",  
                   notes = "- Se lista con el filtro parte del número inicial y se ordenar ascendentemente. \n - Primera tipo de filtro")
    @io.swagger.annotations.ApiResponse(code = 200,response = CuentaContableDTO.class, responseContainer = "List", message = "Se encuentra registros")
    @GetMapping(value="/filtro-numero/1/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<List<CuentaContableDTO>> busquedaPersonalizadaNumero(@PathVariable String numero) {
        System.out.println("Filtro 1");  
        var lista = service.busquedaPersonalizadaNumero(numero);
        if(Objects.nonNull(lista) && lista.size()>0)
            return new ResponseEntity<>(lista,HttpStatus.OK);
        else //return ResponseEntity.notFound().build(); 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /*public ResponseEntity<?> busquedaPersonalizadaNumero(@PathVariable String numero) {
        System.out.println("Filtro 1");        
        return ResponseEntity.ok(service.busquedaPersonalizadaNumero(numero));
    }*/
    @Operation(summary = "Listar todos los registros de la cuenta contable a partir de un numero (filtro tipo 2).",  
            description = "- Se lista con el filtro parte del número inicial y se ordenar ascendentemente. \n- Segundo tipo de filtro. \n- Filtro si usa documento.")
    @GetMapping(value="/filtro-numero/2/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CuentaContableVista> busquedaPersonalista(@PathVariable String numero) {
        System.out.println("Filtro 2");
        return service.findByNumeroStartingWithAndUsaDocumentoTrueOrderById(numero);
    }
    
    @Operation(summary = "Listar todos los registros de la cuenta contable a partir de un numero (filtro tipo 3).",  
            description = "- Se lista con el filtro parte del número inicial y se ordenar ascendentemente. \n - Segundo tipo de filtro. \n - Muestra informacion de la operación de tesoreria.")
    @GetMapping(value="/filtro-numero/3/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CuentaContableCustom> busquedaNumeroOperacionTesoreria(@PathVariable String numero) {
        System.out.println("Filtro 3");
        return service.busquedaNumeroOperacionTesoreria(numero);
    }
    @Operation(summary = "Realiza accion (I/U/D) para el mantenimiento de cuenta contable y algunos procesos más.",  
        description = "- I: Para insertar. \n- U: Para actualizar \n - D: Eliminar")
    @PutMapping(value="/{ruc}/iud/{accion}", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String iudJson(
            @Parameter(description = "RUC de la empresa de usuario que se ha logueado")
            @PathVariable String ruc, 
            @ApiParam(value = "Tipo de accion a realizar", defaultValue = "00",example = "I",allowableValues = "I,U,D") 
            @PathVariable String accion,
            @RequestBody CuentaContableRequest request) throws Exception {
        //System.out.println(request.getOCuentaContable());
        System.out.println("objeto: "+ new ObjectMapper().writeValueAsString(request.getOCuentaContable()));
        return service.iudJson(ruc,accion,request);
    }
}
