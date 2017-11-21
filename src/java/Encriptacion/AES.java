
package Encriptacion;


public class AES {

    
    public String Encriptar(String password) throws Exception{
        String passwordEnc=AESFeo.encrypt(password);
        return passwordEnc;
    }
    
    public String Desencriptar(String passwordEnc) throws Exception{
        String passwordDec=AESFeo.decrypt(passwordEnc);
        return passwordDec;
    }
}
