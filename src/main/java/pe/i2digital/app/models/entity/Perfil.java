/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.models.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_perfil", schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Perfil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_perfil_id")
    private Integer id;
    @Size(max = 50,message = "Debe ser hasta 50 carácteres")
    @Column(name = "tb_perfil_nom")
    private String nombre;
    @JsonRawValue
    @NotNull(message = "Opciones es obligatorio")
    @Column(name = "tb_perfil_opc")
    private String opciones;
}
