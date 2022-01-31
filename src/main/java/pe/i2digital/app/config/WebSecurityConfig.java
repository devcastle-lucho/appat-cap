/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.i2digital.app.security.jwt.JwtAuthenticationEntryPoint;
import pe.i2digital.app.security.jwt.JwtRequestFilter;
import pe.i2digital.app.services.JwtUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity //JWT
@EnableGlobalMethodSecurity(prePostEnabled = true) //JWT_1
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    //Autenticacion S1: Basico Auth // En Memoria
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user_appat_2")                
                .password(passwordEncoder().encode("--#--"))
                .roles("ADMIN", "USER");
                ;
    }*/
    
    //@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic();
    }*/ 

    /*@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }*/
    /*Seguridad JWT Token*/
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    /*@Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPointBean() throws Exception{
        return new JwtAuthenticationEntryPoint();
    }*/
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/authenticate","/api/v1/authenticate",
                        "/swagger-ui.html#/**",
                        "/swagger-ui.html",
                        "/error", "/error/**",
                        "/img/**", "/css/**",
                        "/api/v1/error", "/api/v1/error/**",
                        "/api/v1/img/**", "/api/v1/css/**",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.logout().logoutSuccessUrl("/authenticate").logoutUrl("/logout").permitAll();
    }

}
