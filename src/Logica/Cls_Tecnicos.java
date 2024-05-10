
package Logica;

import Clases.Conectar;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;


/**
 *
 * @author Conejo
 */
public class Cls_Tecnicos {
    private final String SQL_INSERT ="INSERT INTO tecnico (Id,CC,Nombre,Telefono,Fecha) VALUES (?,?,?,?,?)";
    private final String SQL_SELECT ="SELECT * FROM tecnico";
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private final Conectar Conn;
    
    public Cls_Tecnicos(){
        PS = null;
        Conn = new Conectar();   
    }
    
    private DefaultTableModel setTitulos(){
        DT= new DefaultTableModel();
        DT.addColumn("Id");
        DT.addColumn("CC");
        DT.addColumn("Nombre");
        DT.addColumn("Telefono");
        DT.addColumn("Fecha");
        return DT;
    }
    //para insertar datos
    public int insertDatos(int Id,int CC,String Nom,String Tel,String Fecha){
        int rest =0;
        try {
            PS= Conn.getConnectio().prepareStatement(SQL_INSERT);
            PS.setInt(1, Id);
            PS.setInt(2, CC);
            PS.setString(3, Nom);
            PS.setString(4, Tel);
            PS.setString(5, Fecha);
            rest = PS.executeUpdate();
            if(rest > 0){
                JOptionPane.showMessageDialog(null, "Registro Guardado..");
            }
        } catch (SQLException e) {
            System.err.println("Error al Guardar Los Datos: "+ e.getMessage());
        }finally{
         PS = null;
         Conn.close();         
    }
       return  rest;
        
    }
    
    
     // para listar los registros
    public DefaultTableModel getDatos(){
        try {
            setTitulos();
            PS = Conn.getConnectio().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();        
            Object [] fila = new Object [5];
             while (RS.next()){
                 fila[0]= RS.getInt(1);
                 fila[1]= RS.getInt(2);
                 fila[2]= RS.getString(3);
                 fila[3]= RS.getString(4);
                 fila[4]= RS.getString(5);
                 DT.addRow(fila);
             }
                    
        } catch (SQLException e) {
            System.err.println("Error de Visualizacion"+ e.getMessage());
        }finally{
            PS=null;
            RS=null;
            Conn.close();
        }
        return DT;
        
    }
    // para buscar por filtro en la tabla con dos parametros
    
    public DefaultTableModel getDato(int crt,String prm){
        String SQL;
        if (crt == 0){
            SQL = "SELECT * FROM tecnico WHERE CC = " + prm;
        }else{
            SQL = "SELECT * FROM tecnico WHERE Nombre like '" + prm + "%'";
    }          
       
            
        try {
            setTitulos();
            PS = Conn.getConnectio().prepareStatement(SQL);
            RS = PS.executeQuery();        
            Object [] fila = new Object [5];
             while (RS.next()){
                 fila[0]= RS.getInt(1);
                 fila[1]= RS.getInt(2);
                 fila[2]= RS.getString(3);
                 fila[3]= RS.getString(4);
                 fila[4]= RS.getString(5);
                 DT.addRow(fila);
             }
                    
        } catch (SQLException e) {
            System.err.println("Error de Visualizacion"+ e.getMessage());
        }finally{
            PS=null;
            RS=null;
            Conn.close();
        }
        return DT;
     
    }
 
        

    //para insertar datos
    public int ActualizarDatos(int Id,int CC,String Nom,String Tel,String Fecha){
        String SQL = "UPDATE tecnico SET Id='"+Id+"',Nombre='"+Nom+"',Telefono='"+Tel+"',Fecha='"+Fecha+"' WHERE CC="+CC;
        int rest =0;
        try {
            PS= Conn.getConnectio().prepareStatement(SQL);
            rest = PS.executeUpdate();
            if(rest > 0){
                JOptionPane.showMessageDialog(null, "Registro Modificado..");
            }
        } catch (SQLException e) {
            System.err.println("Error al Actualizar Los Datos: "+ e.getMessage());
        }finally{
         PS = null;
         Conn.close();         
    }
       return  rest;
        
    }
    
    public int eliminardatos(String Id){
         String SQL = "DELETE FROM tecnico WHERE Id="+Id;
        int rest =0;
        try {
            PS= Conn.getConnectio().prepareStatement(SQL);
            rest = PS.executeUpdate();
            if(rest > 0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado..");
            }
        } catch (SQLException e) {
            System.err.println("Error al Eliminar Los Datos: "+ e.getMessage());
        }finally{
         PS = null;
         Conn.close();         
    }
      return  rest;  
        
        
    }
    
}

    

