/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.i2digital.app.services.JwtUserDetailsServiceImpl;


@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    /*@Autowired private JwtToken jwtToken;*/
    private final JwtToken jwtTokenUtil;
    public JwtRequestFilter(JwtToken jwtTokenUtil) {
        this.jwtTokenUtil =jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username =null;
        String jwtToken = null;
        if (requestTokenHeader !=null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.error("No se puede obtener el TOKEN JWT");
            } catch (ExpiredJwtException e) {
                log.error("TOKEN JWT expirado");
            }
        } else if(requestTokenHeader == null) {
            log.error("No se proporciona el encabezado de la autorización");
        } else if(!requestTokenHeader.startsWith("Bearer ")) {
            log.error("TOKEN JWT no comienza con Bearer");
        }

        if(username !=null  && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails= this.jwtUserDetailsService.loadUserByUsername(username);

            if(jwtTokenUtil.validateToken(jwtToken,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }    
}
