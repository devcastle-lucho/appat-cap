/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.models.jwt;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtResponse {
    private final String jwttoken;
    
    public String getToken() {
        return this.jwttoken;
    }
}
