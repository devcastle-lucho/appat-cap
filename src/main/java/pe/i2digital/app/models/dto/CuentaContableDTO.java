package pe.i2digital.app.models.dto;

import lombok.Value;

@Value // DTO basado en clase
public class CuentaContableDTO {
    private Integer id;
    private String numero;
    private String nombre;
}

