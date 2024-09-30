
package Controlador;

import Modelo.mdlMecanico;
import Vistas.frmMecanico;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import javax.swing.JOptionPane;


public class ctrlMecanico implements MouseListener{
    
    private mdlMecanico modelo;
    private frmMecanico vista;
    
    public ctrlMecanico(mdlMecanico modelo, frmMecanico vista){
        this.vista = vista;
        this.modelo = modelo;
        
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.jtbMecanico.addMouseListener(this);
        modelo.mostrarMecanicos(vista.jtbMecanico);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == vista.btnAgregar) {
              try {
                modelo.setNombreMecanico(vista.txtNombre.getText());
                modelo.setEdadMecanico(Integer.parseInt(vista.txtEdad.getText()));
                modelo.setPesoMecanico(Double.parseDouble(vista.txtPeso.getText()));
                modelo.setCorreoMecanico(vista.txtCorreo.getText());

                modelo.insertarMecanico();
                modelo.mostrarMecanicos(vista.jtbMecanico);
                modelo.limpiarCampos(vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar mecánico: " + ex.getMessage());
            }
        }
        
        if (e.getSource() == vista.btnEliminar) {
            modelo.eliminarMecanico(vista.jtbMecanico);
            modelo.mostrarMecanicos(vista.jtbMecanico);
            modelo.limpiarCampos(vista);
        }
        
        if (e.getSource() == vista.btnActualizar) {
            try {
                modelo.setNombreMecanico(vista.txtNombre.getText());
                modelo.setEdadMecanico(Integer.parseInt(vista.txtEdad.getText()));
                modelo.setPesoMecanico(Double.parseDouble(vista.txtPeso.getText()));
                modelo.setCorreoMecanico(vista.txtCorreo.getText());

                modelo.actualizarMecanico(vista.jtbMecanico);
                modelo.mostrarMecanicos(vista.jtbMecanico);
                modelo.limpiarCampos(vista);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar mecánico: " + ex.getMessage());
            }

        }
        
        if (e.getSource() == vista.jtbMecanico) {
            modelo.cargarDatos(vista);
        }
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
