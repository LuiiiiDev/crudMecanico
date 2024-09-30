package Controlador;

import Vistas.frmMecanico;
import Vistas.frmWelcome;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ctrlWelcome implements MouseListener{
    
    private frmWelcome vista;
    
    public ctrlWelcome(frmWelcome vista){
        this.vista = vista;
        
        vista.btnIngresar.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == vista.btnIngresar) {
            frmMecanico mecanico = new frmMecanico();
            mecanico.setVisible(true);
            
            vista.dispose();
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
