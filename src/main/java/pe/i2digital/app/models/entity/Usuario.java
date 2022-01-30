/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuario", schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_usuario_id")
    private Integer id;
    @Size(max = 6,message = "Debe ser 6 números")
    @NotNull(message = "Código es obligatorio")
    @Column(name = "tb_usuario_cod", unique = true)
    private String codigo;
    @Column(name = "tb_usuario_apepat")
    private String apellidoPaterno;
    @Column(name = "tb_usuario_apemat")
    private String apellidoMaterno;
    @Column(name = "tb_usuario_nom")
    private String nombre;
    @Column(name = "tb_usuario_corele")
    private String correoElectronico;
    @Column(name = "tb_usuario_nic")
    private String nickname;
    @Column(name = "tb_usuario_con")
    private String password;
    @Column(name = "tb_usuario_est")
    private String estado;
    @Column(name = "tb_usuario_tip")
    private String tipo;
    @JsonIgnoreProperties(value = {"tb_usuario_id", "handler", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_perfil_id")
    private Perfil perfil;    
}
