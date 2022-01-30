/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.models.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pe.i2digital.app.models.entity.Usuario;


public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
    public Optional<Usuario> findByCodigo(String codigo);
    public Optional<Usuario> findByNickname(String nickname);
}
