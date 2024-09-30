
package Controlador;

import Modelo.mdlMecanico;
import Vistas.frmMecanico;
import Vistas.frmRegistroMecanicos;
import Vistas.frmWelcome;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
        vista.btnBuscarMecanico.addMouseListener(this);
        vista.btnVolver.addMouseListener(this);
        
        modelo.mostrarMecanicos(vista.jtbMecanico);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == vista.btnAgregar) {
            try {
                if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || 
                    vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    return;
                }

                if (!vista.txtNombre.getText().matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(null, "El nombre no debe contener números.");
                    return;
                }

                try {
                    Integer.parseInt(vista.txtEdad.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La edad debe ser un número entero.");
                    return;
                }

                try {
                    Double.parseDouble(vista.txtPeso.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El peso debe ser un número decimal.");
                    return;
                }

                if (!vista.txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    JOptionPane.showMessageDialog(null, "El correo no tiene un formato válido.");
                    return;
                }

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
                if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || 
                    vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    return;
                }

                if (!vista.txtNombre.getText().matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(null, "El nombre no debe contener números.");
                    return;
                }

                try {
                    Integer.parseInt(vista.txtEdad.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La edad debe ser un número entero.");
                    return;
                }

                try {
                    Double.parseDouble(vista.txtPeso.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El peso debe ser un número decimal.");
                    return;
                }

                if (!vista.txtCorreo.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    JOptionPane.showMessageDialog(null, "El correo no tiene un formato válido.");
                    return;
                }

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
        
        if (e.getSource() == vista.btnVolver) {
            int respuesta = JOptionPane.showConfirmDialog(null, 
                    "¿Estás seguro de que deseas volver?", 
                    "Confirmación", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.WARNING_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                frmWelcome wel = new frmWelcome();
                wel.setVisible(true);
                vista.dispose();
            }
        }
        
        if (e.getSource() == vista.btnBuscarMecanico) {
            
                frmRegistroMecanicos registroMecanico = new frmRegistroMecanicos();
                registroMecanico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                registroMecanico.setVisible(true);
                
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
