/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;


import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author jhian
 */
public class empleados {
    int idEmpleado;
    String nombre;
    int cedula;
    String correo;
    String telefono;
    private Object Cs;
    private Object paramtbTotal_empleado;
    private Object JtextField1;
    private Object JtextField2;
    private Object JtextField3;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     *
     * @param paramNombre_empleado
     * @param paramCedula
     * @param paramCorreo
     * @param paramTelefono
     */
    public void insertarempleado (JTextField paramNombre_empleado, JTextField paramCedula, JTextField paramCorreo, JTextField paramTelefono){
        setNombre(paramNombre_empleado.getText());
        setCedula(Integer.parseInt(paramCedula.getText()));
        setCorreo(paramCorreo.getText());
        setTelefono(paramTelefono.getText());
        
        //setNombre(paramNombre.getText());
        //int setCedula;
        //setCedula = Integer.parseInt(paramCedula.getText());
        //setCorreo(paramCorreo.getText());
        //int setTelefono;
        //setTelefono = Integer.parseInt(paramTelefono.getText());
        String consulta  = "insert into empleado(Nombre_empleado, Cedula_empleado, Correo_empleado, Telefono_empleado) values (?,?,?,? )";
        conexion objetoConexion = new conexion();
            try {
                CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
                 // aqui esta el error, debes revisar la base de datos //
                cs.setString(1, getNombre());
                cs.setInt(2, getCedula());
                cs.setString(3, getCorreo());
                cs.setString(4, getTelefono());
                cs.execute ();
                
                JOptionPane.showMessageDialog(null, " Se inserto correctamente el empleado");
            } catch(HeadlessException | SQLException e) {
             JOptionPane.showMessageDialog(null, " No se inserto correctamente el empleado, ERROR"+ e.toString());
             System.out.println("error!:"+e.toString());
            }
    }

    public void listarempleado(JTable paramtbTotal_empleados){
        conexion objetoConexion = new conexion();
        
        DefaultTableModel modelo = new DefaultTableModel(
           new String[] {"id", "Nombre", "Cedula", "Correo", "Telefono"},
        0
        );
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<>(modelo);
        paramtbTotal_empleados.setRowSorter(OrdenarTabla);
        paramtbTotal_empleados.setModel(modelo);
        
        String sql = "select * from empleado";
        
        try {
            Connection conexion = objetoConexion.conectar();
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
            String[] datos = new String[5];
            datos[0] = rs.getString(1); // id
            datos[1] = rs.getString(2); // Nombre
            datos[2] = rs.getString(3); // Cedula
            datos[3] = rs.getString(4); // Correo
            datos[4] = rs.getString(5); // Telefono

            modelo.addRow(datos);
        }
            
            paramtbTotal_empleados.setModel(modelo);
        } catch (SQLException e) {
            
         JOptionPane.showMessageDialog(null, " no se pudo mostrar los registros: " + e.toString());
        } 
    } 
    
    /**
     *
     * @param paramtbTotal_empleado
     * @param paramId
     * @param paramNombre_empleado
     * @param paramCedula
     * @param paramCorreo
     * @param paramTelefono
     */
    public void  seleccionarEmpleado (JTable paramtbTotal_empleado,JTextField paramId, JTextField paramNombre_empleado, JTextField paramCedula, JTextField paramCorreo, JTextField paramTelefono){ 
  
      try {
          
          int fila = paramtbTotal_empleado.getSelectedRow();
          if (fila >= 0) {
          
              paramId.setText((String) (paramtbTotal_empleado.getValueAt(fila, 0 )));
              paramNombre_empleado.setText((String)(paramtbTotal_empleado.getValueAt(fila, 1 )));
              paramCedula.setText((String) (paramtbTotal_empleado.getValueAt(fila, 2 )));
              paramCorreo.setText((String) (paramtbTotal_empleado.getValueAt(fila, 3 )));
              paramTelefono.setText((String) (paramtbTotal_empleado.getValueAt(fila, 4 )));
              
          }
          
          else{
          
          JOptionPane.showMessageDialog(null, " fila no seleccionada");
          }
              
              
      } catch (HeadlessException e) {
     JOptionPane.showMessageDialog(null, " error de seleccion, {ERROR" + e.toString());     
          
      }
  
  }
    public void ModificarEmpleados (JTextField paramIdEmpleado,JTextField paramNombre_empleado, JTextField paramCedula, JTextField paramCorreo, JTextField paramTelefono ){
    
    setIdEmpleado(Integer.parseInt(paramIdEmpleado.getText()));
    setNombre( paramNombre_empleado.getText());
        setCedula(Integer.parseInt(paramCedula.getText()));
        setCorreo(paramCorreo.getText());
        setTelefono(paramTelefono.getText());
    
        
        conexion objetoConexion = new conexion();
        
        String consulta = "UPDATE empleado SET empleado.Nombre_empleado = ?, empleado.Cedula_empleado = ?, empleado.Correo_empleado = ?, empleado.Telefono_empleado = ? WHERE empleado.IdEmpleado =? ;";
   
        try {
            
            CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
             cs.setString(1, getNombre());
                cs.setInt(2, getCedula());
                cs.setString(3, getCorreo());
                cs.setString(4, getTelefono());
                cs.setInt(5, getIdEmpleado());
                
                cs.execute ();
            
                JOptionPane.showMessageDialog(null, "modificacion exitosa");
            
        } catch (HeadlessException | SQLException e) {
            
          JOptionPane.showMessageDialog(null, "No se modifico, ERROR" + e.toString());  
        }
    }
    
    /**
     *
     * @param paramIdEmpleado
     */
    public void Eliminarempleado (JTextField paramIdEmpleado){
        
    setIdEmpleado(Integer.parseInt(paramIdEmpleado.getText()));
    
    conexion objetoConexion = new conexion();
       
    String consulta = " DELETE FROM empleado WHERE `empleado`.`idempleado` =?";
    
        try {
            
        CallableStatement cs = objetoConexion.conectar().prepareCall(consulta);
        
        cs.setInt(1, getIdEmpleado());
        
         cs.execute ();
         
         JOptionPane.showMessageDialog(null, " Se elimino correctamente ");
        
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, Error" + e.toString());  
           
        }
 
    }

    
}
    
        
   
    
        
     
        

      
    
            
    

