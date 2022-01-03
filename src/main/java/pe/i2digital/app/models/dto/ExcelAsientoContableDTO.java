package pe.i2digital.app.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ExcelAsientoContableDTO {
    private Date fecha;
    private String glosa;
    private String numero;
    private String operacionContable;
    private String cuentaContable;
    private Double debeSoles;
    private Double haberSoles;
    private String documento;
}
