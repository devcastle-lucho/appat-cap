package pe.i2digital.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_estaciontrabajo", schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstacionTrabajo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_estaciontrabajo_id")
    private Integer id;
    @Size(max = 2)
    @NotNull(message = "Código es obligatorio")
    @Column(name = "tb_estaciontrabajo_cod", unique = true)
    private String codigo;

    @Size(max = 50)
    @NotNull(message = "Nombre es obligatorio")
    @Column(name = "tb_estaciontrabajo_nom", unique = true)
    private String nombre;

    @JsonRawValue
    @NotNull(message = "Anfitrion es obligatorio")
    @Column(name = "tb_estaciontrabajo_anf")
    private String anfitrion;

    @JsonIgnoreProperties(value = {"estacionesTrabajo", "handler", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_sucursal_id")
    private Sucursal sucursal;
}
