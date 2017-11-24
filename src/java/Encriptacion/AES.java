
package Encriptacion;


public class AES {

    
    public String Encriptar(String password, String correo) throws Exception{
        String passwordEnc=AESFeo.encrypt(password,  correo);
        return passwordEnc;
    }
    
    public String Desencriptar(String passwordcif, String correo) throws Exception{
        String passwordDec=AESFeo.decrypt(passwordcif, correo);
        return passwordDec;
    }
}
