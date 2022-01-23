package pe.i2digital.app.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.i2digital.app.services.EstacionTrabajoService;
import pe.i2digital.app.services.SucursalService;

@RequestMapping("/api/v1/estaciontrabajo")
@RestController(value="estaciontrabajo")
@Slf4j
public class EstacionTrabajoController {
    @Autowired
    private EstacionTrabajoService service;
    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> devolver(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping("/filtro-codigo/{codigo}")
    public ResponseEntity<?> devolver(@PathVariable String codigo) {
        return ResponseEntity.ok(service.findByCodigo(codigo));
    }
    @GetMapping(value="/filtro",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscarxCodigo(@RequestParam String codigo) {
        return ResponseEntity.ok(service.findByCodigo(codigo));
    }
    @PostMapping(value="/upload-info", produces = {MediaType.APPLICATION_JSON_VALUE,
                                                   MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> cargarInfo(@RequestPart(value = "codigo",required = true) String codigo,
            @RequestPart List<MultipartFile> file
            ) throws Exception {
        log.info(String.format("Codigo: %s", codigo));
        var bytes= file.get(0).getBytes();
        String sJson = new String(bytes, StandardCharsets.UTF_8);
        log.info(String.format("JSON: %s", sJson));
        return ResponseEntity.ok().build();
    }
}
