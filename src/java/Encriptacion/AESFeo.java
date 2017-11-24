package Encriptacion;

import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import servlets.Conexion;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESFeo {

    static String correoUsuario="";
    static char a, b, c, d;
    
    private static final String Algo = "AES";
    private static final byte[] keyValue = new byte[]{a,b,c,d,a,b,c,d,a,b,c,d,a,b,c,d};

    public static String encrypt(String Data, String username) throws Exception {
        correoUsuario = buscarCorreo(username, Data);
        a=caractera();
        b=caracterb();
        c=caracterc();
        d=caracterd();
        
        Key key = generateKey();
        Cipher c = Cipher.getInstance(Algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValores = c.doFinal(Data.getBytes());
        String encriptadoValores = new BASE64Encoder().encode(encValores);
        return encriptadoValores;
    }

    public static String decrypt(String encryptData, String username) throws Exception {
        correoUsuario = buscarCorreo(username, encryptData);
        a=caractera();
        b=caracterb();
        c=caracterc();
        d=caracterd();
        
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
    
    public static String buscarCorreo(String user, String contra){
            String correo="";
                try{
                    Conexion c = new Conexion();
                    Connection con = c.getConexion();
                    
                    if (con!=null){
                        String sql = "SELECT * FROM usuario WHERE"
                                + " Username_Usuario='"+user+"' && "
                                + "Contrasenia_Usuario='"+contra+"';";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery(); 
                        
                        if (rs.next()){
                            correo = rs.getString("Correo_Usuario");
                        }
                        c.cerrarConexion();
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
        return correo;
        }
    
    public static char caractera(){
        char a = 0;
        
        a=correoUsuario.charAt(0);
        
        return a;
    }
    
    public static char caracterb(){
        char a = 0;
        
        a=correoUsuario.charAt(1);
        
        return a;
    }
    
    public static char caracterc(){
        char a = 0;
        
        a=correoUsuario.charAt(2);
        
        return a;
    }
    
    public static char caracterd(){
        char a = 0;
        
        a=correoUsuario.charAt(3);
        
        return a;
    }
}
