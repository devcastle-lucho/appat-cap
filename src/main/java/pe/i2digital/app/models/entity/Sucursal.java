package pe.i2digital.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_sucursal", schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_sucursal_id")
    private Integer id;
    @Size(max = 2)
    @NotNull(message = "Código es obligatorio")
    @Column(name = "tb_sucursal_cod", unique = true)
    private String codigo;

    @Size(max = 50)
    @NotNull(message = "Nombre es obligatorio")
    @Column(name = "tb_sucursal_nom", unique = true)
    private String nombre;
    @Size(max = 4)
    @Column(name = "tb_sucursal_codestane", unique = true)
    private String codigoEstablecimiento;
    @Size(max = 200)
    @NotNull(message = "Direccion completa es obligatorio")
    @Column(name = "tb_sucursal_dircomdet")
    private String direccionCompleta;
    @Size(max = 50)
    @Column(name = "tb_sucursal_tel")
    private String telefono;
    @Size(max = 50)
    @Column(name = "tb_sucursal_corele")
    private String correoElectronico;
    @Size(max = 1)
    @NotNull(message = "Tipo es obligatorio")
    @Column(name = "tb_sucursal_tip")
    private String tipo;
    @JsonIgnoreProperties(value = {"sucursal", "handler", "hibernateLazyInitializer"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<EstacionTrabajo> estacionesTrabajo;
}
