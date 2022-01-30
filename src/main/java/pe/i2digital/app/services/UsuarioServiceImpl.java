
package pe.i2digital.app.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.exception.EntityNotFoundException;
import pe.i2digital.app.models.entity.Usuario;
import pe.i2digital.app.models.repository.UsuarioRepository;
import pe.i2digital.app.utils.EncryptDecryptUtil;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository repository;
    @Override
    public Optional<Usuario> findByCodigo(String codigo) throws Exception{
        var oUsuario = repository.findByCodigo(codigo);
        var usuario = oUsuario.orElseThrow(()-> new EntityNotFoundException(Usuario.class,"codigo",codigo));
        String emailDecrypt = EncryptDecryptUtil.decryptPhrase(usuario.getCorreoElectronico());
        String passwordDecrypt = EncryptDecryptUtil.decryptPhrase(usuario.getPassword());
        log.warn("email: "+emailDecrypt);
        log.warn("password: "+passwordDecrypt);
        return oUsuario;
    }

    @Override
    public Optional<Usuario> findByNickname(String nickname) {
        return repository.findByNickname(nickname);
    }    

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }
}
