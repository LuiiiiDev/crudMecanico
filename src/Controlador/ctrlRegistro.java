
package Controlador;

import Modelo.mdlMecanico;
import Vistas.frmRegistroMecanicos;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ctrlRegistro implements MouseListener, KeyListener{
    
    
    private mdlMecanico modelo;
    private frmRegistroMecanicos vista;
    
    public ctrlRegistro(mdlMecanico modelo, frmRegistroMecanicos vista){
        this.vista = vista;
        this.modelo = modelo;
        
        vista.txtBuscar.addKeyListener(this);
        modelo.mostrarMecanicos(vista.jtbRegistro);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if (e.getSource() == vista.txtBuscar) {
            modelo.buscarMecanico(vista.jtbRegistro, vista.txtBuscar);
        }
    }
    
}
