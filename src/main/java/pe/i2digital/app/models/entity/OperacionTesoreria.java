package pe.i2digital.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_cuentacontable_id")
    private Integer id;
    @Size(max = 100)
    @NotNull(message = "Número es obligatorio")
    @Column(name = "tb_operaciontesoreria_nom")
    private String nombre;
    @OneToOne // Relacion uno a uno
    @JoinColumn(name = "tb_cuentacontable_id") //Con que atributo se vincula
    @MapsId  //Mapeo de id compartido
    private CuentaContable cuentaContable;
}
