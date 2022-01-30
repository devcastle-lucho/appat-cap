/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.services;

import java.util.List;
import java.util.Optional;
import pe.i2digital.app.models.entity.Usuario;

/**
 *
 * @author Luis
 */
public interface UsuarioService {
    public Optional<Usuario> findByCodigo(String codigo) throws Exception;
    public Optional<Usuario> findByNickname(String nickname);
    public List<Usuario> findAll();
}
