/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author rodri
 */
public class Comprobar {
    
    public boolean buscarUsuario(String user, String contra){
        boolean existente=false;
                try{
                    Conexion c = new Conexion();
                    Connection con = c.getConexion();
                    
                    if (con!=null){
                        String sql = "SELECT * FROM usuario WHERE"
                                + " Username_Usuario='"+user+"' AND "
                                + "Contrasenia_Usuario='"+contra+"'";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery(); 
                        
                        if (rs.next()){
                            existente=true;
                        }
                        c.cerrarConexion();
                    }
                }
                catch(SQLException e){
                    existente=false;
                    e.printStackTrace();
                }
        return existente;
    }
    
}
