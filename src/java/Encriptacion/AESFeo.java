package Encriptacion;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESFeo {

    private static final String Algo = "AES";
    private static final byte[] keyValue = new byte[]{'h', 'o', 'l', 'a', 'v', 'a', 'n', 'a', 'v', 'a', 'l', 'e', 'r', 'w', 'i', 'i'};

    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(Algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValores = c.doFinal(Data.getBytes());
        String encriptadoValores = new BASE64Encoder().encode(encValores);
        return encriptadoValores;
    }

    public static String decrypt(String encryptData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(Algo);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodificarValores = new BASE64Decoder().decodeBuffer(encryptData);
        byte[] descifrarvalores= c.doFinal(decodificarValores);
        String decodificartexto = new String(descifrarvalores);
        return decodificartexto;
    }

    private static Key generateKey() {
        Key key = new SecretKeySpec(keyValue, Algo);
        return key;
    }
}
