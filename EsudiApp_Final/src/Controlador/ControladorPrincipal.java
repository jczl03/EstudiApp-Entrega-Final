/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Clases.Tarea;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
/**
 *
 * @author jczam
 */
public class ControladorPrincipal implements ActionListener{
    private FramePrincipal frame;
    private ConTareas controladorPanelTareas;
    Connection conexion;
    public ControladorPrincipal(FramePrincipal frame,Connection conexion){
        this.frame=frame;
        this.conexion=conexion;
        iniciarControladores();
        frame.anadirButtonsActionListener(this);
        frame.updateTareaHome(controladorPanelTareas.getTareaMasUrgente());
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==frame.getBtnTareas()){
            
            
            controladorPanelTareas.updatePanel();
            frame.unhidePanelTareas();
            frame.hidePanelSecundarios();
            frame.hideHome();
            frame.hidePanelAsignaturas();
        }else if(e.getSource()==frame.getBtnAsignaturas()){
            frame.hidePanelTareas();
            frame.hidePanelSecundarios();
            frame.unhidePanelAsignaturas();
            frame.hideHome();
        }else if(e.getSource()==frame.getHomeButton()){
            frame.updateTareaHome(controladorPanelTareas.getTareaMasUrgente());
            frame.unhideHome();
            frame.hidePanelTareas();
            frame.hidePanelSecundarios();
            frame.hidePanelAsignaturas();
        }
    }

    private void iniciarControladores() {
        controladorPanelTareas= new ConTareas(frame.getPanelTareas(),frame.getPanelCrearTareas(),frame.getPanelVerTareas(),frame.getMiniTareasPanel(),frame.getAsignaturaPanel(),conexion);
    }
    
}
