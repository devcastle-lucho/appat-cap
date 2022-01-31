package pe.i2digital.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.i2digital.app.exception.domain.AuthAppException;
import pe.i2digital.app.models.jwt.JwtRequest;
import pe.i2digital.app.models.jwt.JwtResponse;
import pe.i2digital.app.security.jwt.JwtToken;
import pe.i2digital.app.services.JwtUserDetailsServiceImpl;

@RestController
//@CrossOrigin
@Slf4j
public class AuthController {
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> crearTokenAutenticacion( @RequestBody JwtRequest request) throws Exception{
        autenticar(request.getUsername(),request.getPassword());
        final UserDetails userDetails= jwtUserDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public void autenticar(String username, String password) throws AuthAppException {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );
        }catch(DisabledException e) {
            throw  new AuthAppException("USUARIO_DESHABILITADO",e);
        }catch (BadCredentialsException e) {
            throw  new AuthAppException("CREDENCIALES_INVALIDOS",e);
        }
    }
}
