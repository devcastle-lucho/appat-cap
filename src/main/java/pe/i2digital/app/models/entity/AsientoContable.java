package pe.i2digital.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AsientoContable {
    private Integer id;
    private String numero;
    private Date fecha;
    private String glosa;
    private String estado;
    private TipoOperacionContable oTipoOperacionContable;

}
