/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.i2digital.app.utils;

import com.google.common.io.BaseEncoding;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class EncriptadorPassword {
    Cipher ecipher;
    Cipher dcipher;
    
    //BASE 64
    public EncriptadorPassword (SecretKey key,String algoritmo) throws Exception{
        try {
            ecipher = Cipher.getInstance(algoritmo);
            dcipher = Cipher.getInstance(algoritmo);
            
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public String encriptado (String cadena) throws Exception{
        try {
            byte[] utf8 =cadena.getBytes("utf8");
            byte[] encriptador = ecipher.doFinal(utf8);
            return BaseEncoding.base64().encode(encriptador);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public String desencriptado(String cadena) throws Exception{
        try {
            byte[] desencriptador = BaseEncoding.base64().decode(cadena);
            byte[] utf8 = dcipher.doFinal(desencriptador);
            return new String(utf8,"utf8");// ISO lATIN, ISO INGLES
        } catch (Exception e) {
            throw e;
        }
    }
    
    //MD5
    public EncriptadorPassword(String frasePassword) throws Exception{
        try {
            //©ÂÈ2V4Ã£
            byte [] ap = {(byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
                           (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03};
            int cantidadInteraccion = 19;
            KeySpec palabraClave = new PBEKeySpec(frasePassword.toCharArray(),ap, cantidadInteraccion);
            SecretKey  key= SecretKeyFactory.getInstance("PBEwithMD5AndDES").generateSecret(palabraClave);
        
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            
            AlgorithmParameterSpec especificacionParametro = new PBEParameterSpec(ap, cantidadInteraccion);
            
            ecipher.init(Cipher.ENCRYPT_MODE, key, especificacionParametro);
            dcipher.init(Cipher.DECRYPT_MODE, key, especificacionParametro);
        } catch (Exception e) {
            throw e;
        }
    }
}
