package pe.i2digital.app.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_at_operaciontesoreria",schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperacionTesoreria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_cuentacontable_id")
    private Integer id;
    @Size(max = 100)
    @NotNull(message = "Número es obligatorio")
    @Column(name = "tb_operaciontesoreria_nom")
    private String nombre;
}
