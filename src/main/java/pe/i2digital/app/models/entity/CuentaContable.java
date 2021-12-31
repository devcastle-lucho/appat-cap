package pe.i2digital.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_at_cuentacontable",schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuentaContable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_cuentacontable_id")
    private Integer id;
    @Size(max = 50)
    @NotNull(message = "Número es obligatorio")
    @Column(name = "tb_cuentacontable_num", unique = true)
    private String numero;
    @Size(max = 100)
    @NotNull(message = "Nombre es obligatorio")
    @Column(name = "tb_cuentacontable_nom")
    private String nombre;
    @Size(max = 1)
    @Column(name = "tb_cuentacontable_mon")
    private String moneda;
    @Column(name = "tb_cuentacontable_usadoc")
    private Boolean usaDocumento;
}
