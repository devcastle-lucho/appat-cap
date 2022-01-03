package pe.i2digital.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoOperacionContable {
    private Integer id;
    private String codigo;
    private String nombre;
}
