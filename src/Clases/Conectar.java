/*
*con esta clase nos conecatmos ala base de datos
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/**
 *
 * @author Conejo
 */
public class Conectar {
    private static Connection conn;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSW = "1037572712";
    private static final String URL = "jdbc:mysql://localhost:3306/ruta?autoReconnect=true&useSSL=false";

    public Conectar() {
        conn = null;
}
    public Connection getConnectio(){
        //Con este establesco la coneccion
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASSW);                
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Conexcion No Exitosa..",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return conn;
    }
          
    //este metodo nos desconetamos de la base de datos
        public void close(){
            try {
                conn.close();
                                                 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error al realizar la Des_Conexcion..",JOptionPane.ERROR_MESSAGE);
                
            }
        
        }
    }
    

