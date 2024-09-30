
package Modelo;

import Vistas.frmMecanico;
import java.sql.*;
import java.util.HashSet;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class mdlMecanico {

    private String uuidMecanico ;
    private String nombreMecanico ;
    private Number edadMecanico;
    private Double pesoMecanico;
    private String correoMecanico;
    
    public String getUuidMecanico() {
        return uuidMecanico;
    }

    public void setUuidMecanico(String uuidMecanico) {
        this.uuidMecanico = uuidMecanico;
    }

    public String getNombreMecanico() {
        return nombreMecanico;
    }

    public void setNombreMecanico(String nombreMecanico) {
        this.nombreMecanico = nombreMecanico;
    }

    public Number getEdadMecanico() {
        return edadMecanico;
    }

    public void setEdadMecanico(Number edadMecanico) {
        this.edadMecanico = edadMecanico;
    }

    public Double getPesoMecanico() {
        return pesoMecanico;
    }

    public void setPesoMecanico(Double pesoMecanico) {
        this.pesoMecanico = pesoMecanico;
    }

    public String getCorreoMecanico() {
        return correoMecanico;
    }

    public void setCorreoMecanico(String correoMecanico) {
        this.correoMecanico = correoMecanico;
    }
    
    
      public void insertarMecanico() {
        Connection conexion = ClaseConexion.getConexion();

        try {
            PreparedStatement addMecanico = conexion.prepareStatement(
                "insert into tbMecanico (uuidMecanico, nombreMecanico, edadMecanico, pesoMecanico, correoMecanico) values (?, ?, ?, ?, ?)"
            );
            addMecanico.setString(1, UUID.randomUUID().toString());
            addMecanico.setString(2, getNombreMecanico());
            addMecanico.setInt(3, Integer.parseInt(getEdadMecanico().toString()));
            addMecanico.setDouble(4, getPesoMecanico());
            addMecanico.setString(5, getCorreoMecanico());

            addMecanico.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Mecánico insertado con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar mecánico: " + ex.getMessage());
        }
    }

    public void actualizarMecanico(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String uuidMecanico = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                String query = "update tbMecanico set nombreMecanico = ?, edadMecanico = ?, pesoMecanico = ?, correoMecanico = ? where uuidMecanico = ?";
                PreparedStatement actualizarMecanico = conexion.prepareStatement(query);

                actualizarMecanico.setString(1, getNombreMecanico());
                actualizarMecanico.setInt(2, Integer.parseInt(getEdadMecanico().toString()));
                actualizarMecanico.setDouble(3, getPesoMecanico());
                actualizarMecanico.setString(4, getCorreoMecanico());
                actualizarMecanico.setString(5, uuidMecanico);

                actualizarMecanico.executeUpdate();
                JOptionPane.showMessageDialog(null, "Mecánico actualizado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar mecánico: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un mecánico para actualizar.");
        }
    }

    public void mostrarMecanicos(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Código Mecánico", "Nombre", "Edad", "Peso", "Correo"});

        try {
            String query = "select * from tbMecanico";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("uuidMecanico"),
                    rs.getString("nombreMecanico"),
                    rs.getInt("edadMecanico"),
                    rs.getDouble("pesoMecanico"),
                    rs.getString("correoMecanico")
                });
            }

            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar mecánicos: " + ex.getMessage());
        }
    }

    public void eliminarMecanico(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String uuidMecanico = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                String sql = "delete from tbMecanico where uuidMecanico = ?";
                PreparedStatement eliminarMecanico = conexion.prepareStatement(sql);
                eliminarMecanico.setString(1, uuidMecanico);
                eliminarMecanico.executeUpdate();
                JOptionPane.showMessageDialog(null, "Mecánico eliminado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar mecánico: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un mecánico para eliminar.");
        }
    }
    
        public void buscarMecanico(JTable tabla, JTextField miTextField){

            Connection conexion = ClaseConexion.getConexion();


            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new Object[]{"Código Mecánico", "Nombre", "Edad", "Peso", "Correo"});

            try {
                String query = "SELECT uuidMecanico, nombreMecanico, edadMecanico, pesoMecanico, correoMecanico FROM tbMecanico WHERE nombreMecanico LIKE ? || '%'";
                PreparedStatement buscar = conexion.prepareStatement(query);
                buscar.setString(1, miTextField.getText());

                ResultSet rs = buscar.executeQuery();

                  while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getString("uuidMecanico"),
                        rs.getString("nombreMecanico"),
                        rs.getInt("edadMecanico"),
                        rs.getDouble("pesoMecanico"),
                        rs.getString("correoMecanico")
                    });
                }

                 tabla.setModel(modelo);
                tabla.getColumnModel().getColumn(0).setMinWidth(0);
                tabla.getColumnModel().getColumn(0).setMaxWidth(0);
                tabla.getColumnModel().getColumn(0).setWidth(0);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro al buscar mecanico" + ex.getMessage());
                System.out.println(ex);
            }



        }
    
    public void cargarDatos(frmMecanico vista){
        
        int filaSeleccionada = vista.jtbMecanico.getSelectedRow();
        
        if (filaSeleccionada != -1) {
            
            String uuidMecanico = vista.jtbMecanico.getValueAt(filaSeleccionada, 0).toString();
            String nombreMecanico = vista.jtbMecanico.getValueAt(filaSeleccionada, 1).toString();
            String edadMecanico = vista.jtbMecanico.getValueAt(filaSeleccionada, 2).toString();
            String pesoMecanico = vista.jtbMecanico.getValueAt(filaSeleccionada, 3).toString();
            String correMecanico = vista.jtbMecanico.getValueAt(filaSeleccionada, 4).toString();
            
            vista.txtNombre.setText(nombreMecanico);
            vista.txtEdad.setText(edadMecanico);
            vista.txtPeso.setText(pesoMecanico);
            vista.txtCorreo.setText(correMecanico);
            
            
        }
        
    }
    
     public void limpiarCampos(frmMecanico vista){
         
         vista.txtNombre.setText("");
         vista.txtEdad.setText("");
         vista.txtPeso.setText("");
         vista.txtCorreo.setText("");
        
    }
    
}
