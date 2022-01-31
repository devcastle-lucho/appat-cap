/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pe.i2digital.app.models.repository.UsuarioRepository;
import pe.i2digital.app.utils.EncryptDecryptUtil;


@Component
@Slf4j
public class JwtUserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByNickname(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no se encuentra"));

        String passwordDecrypt = EncryptDecryptUtil.decryptPhrase(usuario.getPassword());
        log.warn("Password: "+passwordDecrypt);
        String perfil = usuario.getPerfil().getNombre();
        List<GrantedAuthority> autorizaciones = Arrays.asList(new SimpleGrantedAuthority("ROLE_"+perfil));
        log.warn("Perfil: "+perfil);
        return new User(usuario.getNickname(), new BCryptPasswordEncoder().encode(passwordDecrypt), autorizaciones);
    }
    
}
