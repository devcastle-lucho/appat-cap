/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.utils;



public class EncryptDecryptUtil {

    public static String decryptPhrase(String phrase) throws Exception {
        if (phrase == null) {
            return null;
        }
        EncriptadorPassword encriptadorPassword = new EncriptadorPassword("SGCR");
        String claveDesencripatado;
        try {
            claveDesencripatado = encriptadorPassword.desencriptado(phrase);
        } catch (Exception e) {
            claveDesencripatado = null;
        }
        return claveDesencripatado;
    }

    public static String encryptPhrase(String password) throws Exception {
        String encrypt = null;
        EncriptadorPassword encriptadorPassword = new EncriptadorPassword("SGCR");
        encrypt = encriptadorPassword.encriptado(password);
        return encrypt;
    }
}
