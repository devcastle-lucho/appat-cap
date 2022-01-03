package pe.i2digital.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetalleAsientoContable {
    private Integer id;
    private String proposito;
    private String tipo;
    private String modo;
    private Double importeSoles;
    private Double importeDolares;
    private AsientoContable oAsientoContable;
    private Documento oDocumento;
}
