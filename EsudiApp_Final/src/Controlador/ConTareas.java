/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Main.*;
import Clases.*;
import Estructuras.ArregloDinamico;
import Estructuras.Fila;
import Modelo.BD.ConsultAsignaturas;
import Modelo.BD.ConsultMiniTareas;
import Modelo.BD.ConsultTareas;
import Vista.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author jczam
 */
public class ConTareas {
    private ConsultAsignaturas consultAsig;
    private ConsultTareas consultTareas;
    private ConsultMiniTareas consultMini;
    private VerTareaPanel panelVerTareas;
    private PanelTareas panelTareas;
    private creacionTareasPanel crearTareasPanel;
    private MinitareasPanel miniTareasPanel;
    private CrearAsignatura asignaturaPanel;
    public ConTareas(PanelTareas panelTareas,creacionTareasPanel crearTareasPanel,VerTareaPanel panelVerTareas,MinitareasPanel miniTareasPanel,CrearAsignatura asignaturaPanel,Connection conexion){
        this.panelTareas=panelTareas;
        this.crearTareasPanel=crearTareasPanel;
        this.panelVerTareas=panelVerTareas;
        this.miniTareasPanel=miniTareasPanel;
        this.asignaturaPanel=asignaturaPanel;
        consultAsig=new ConsultAsignaturas(conexion);
        consultTareas=new ConsultTareas(conexion);
        consultMini=new ConsultMiniTareas(conexion);
        startControl();
        //consultMini.crearInstruccion(1,3,"aaaxd");
        //consultMini.crearRecordatorio(1,2,"2021-08-23","08:00","Recordatorio prueba","Descripcion");
    }

    private void fillTareasCombo(ActionEvent ae) {
        
        //panelTareas.fillTareas(consultTareas.getTareas());
        //Fila<Tarea> at=new Fila<Tarea>();
        
        panelTareas.fillTareas(consultTareas.getTareas(panelTareas.getAsignaturaSelected(),consultMini));
        panelTareas.eraseTexts();
       // System.out.println(panelTareas.getAsignaturaSelected().getId());
    }
    public void startControl(){
        fillMaterias();
        panelTareas.addMateriaComboBoxActionListener(ae->fillTareasCombo(ae));
        panelTareas.addTareaComboBoxActionListener(av->setTexts(av));
        panelTareas.addAnadirButtonActionListener(xd->abrirCrearTareaPanel(xd));
        panelTareas.addAccederButtonActionListener(e->verTarea(e));
        crearTareasPanel.addCancelarButtonActionListener(a->cancelarCrearTarea(a));
        crearTareasPanel.addCrearButtonActionListener(x->crearTarea(x));
        miniTareasPanel.addActionListenerToRadio(ae->hideUnhideFields(ae));
        miniTareasPanel.setButtonsActionListener(ae->miniTareaButtons(ae));
        asignaturaPanel.addButtonsActionListeners(a->CreacionAsignatura(a));
    }

    public void fillMaterias(){
        try {
            panelTareas.fillMaterias(consultAsig.getAsignaturas());
        } catch (SQLException ex) {
            Logger.getLogger(ConTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTexts(ActionEvent ae) {
        panelTareas.setTexts(panelTareas.getTareaSelected());
      //  panelTareas.
    }
    public void updatePanel(){
        if(!panelTareas.isVisible()){
            try {
                panelTareas.hideTxt();
                panelTareas.eraseTexts();
                panelTareas.fillMaterias(consultAsig.getAsignaturas());
                panelTareas.hideTareasComboBox();
                // panelTareas.eraseComboBox();
                //fillMaterias();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
        
    }
    public void abrirCrearTareaPanel(ActionEvent ae){
        panelTareas.setVisible(false);
        crearTareasPanel.setVisible(true);
        crearTareasPanel.setAsignatura(panelTareas.getAsignaturaSelected());
    }
    public void cancelarCrearTarea(ActionEvent ae){
        panelTareas.setVisible(true);
        crearTareasPanel.setVisible(false);
        crearTareasPanel.cleanFields();
    }
    public void crearTarea(ActionEvent ae){
        
        if(isValidCreateTarea1()){
            crearTareasPanel.setVisible(false);
            miniTareasPanel.setVisible(true);
            crearTareasPanel.setTarea();
            miniTareasPanel.setCurrentTarea(crearTareasPanel.getNewTarea());
            miniTareasPanel.setTittle();
            miniTareasPanel.updateMiniTareaTitle();
        }else{
             JOptionPane.showMessageDialog(null, "Ingrese valores válidos por favor");
        }
        
    }
    private boolean isValidCreateTarea1() {
        return crearTareasPanel.validateImportancia() && crearTareasPanel.validateNombre();
    }
    public void verTarea(ActionEvent ae){
        if(panelTareas.getTareaSelected()!=null){
            panelTareas.setVisible(false);
            panelVerTareas.setVisible(true);
            panelVerTareas.setTarea(panelTareas.getTareaSelected());
           System.out.println(panelTareas.getTareaSelected());
           panelVerTareas.updatePanel();
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una tarea válida por favor");
        }
        
    }
    public void hideUnhideFields(ActionEvent ae){
        miniTareasPanel.setFieldsAndTexts();
    }
    public void miniTareaButtons(ActionEvent ae){
        if(ae.getSource()==miniTareasPanel.getCancelarButton()){
            miniTareasPanel.setVisible(false);
            crearTareasPanel.setVisible(true);
            miniTareasPanel.setCurrentTarea(null);
            miniTareasPanel.cleanFields();
            miniTareasPanel.restartCurrentMini();
            
            miniTareasPanel.updateMiniTareaTitle();
                    
        }else if(ae.getSource()==miniTareasPanel.getAnadirButton()){
            addMiniTarea();
            miniTareasPanel.cleanFields();
        }else if(ae.getSource()==miniTareasPanel.getFinalizarButton()){
            if(crearTarea(crearTareasPanel.getNewTarea())){
                JOptionPane.showMessageDialog(null, "Tarea creada correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "Ocurrió un error,puede que algunas minitareas no se hayan añadido correctamente :/");
            }
            miniTareasPanel.setVisible(false);
            panelTareas.setVisible(true);
            miniTareasPanel.setCurrentTarea(null);
            miniTareasPanel.cleanFields();
            miniTareasPanel.restartCurrentMini();
            
            miniTareasPanel.updateMiniTareaTitle();
            miniTareasPanel.deleteTarea();
            miniTareasPanel.setTittle();
        }
    }
    public boolean crearTarea(Tarea tarea){
        boolean ret=true;
        if(!consultTareas.register(tarea.getNombre(),tarea.getAsignatura(),tarea.getFecha().toString(),tarea.getHora().toString(),tarea.getImportancia())){ //String nombre, int idAsignatura,String fecha,String hora,int importancia
            ret=false;
        }
        int id=consultTareas.getId(tarea.getNombre(), tarea.getAsignatura());
        ArregloDinamico<MiniTarea> lista=tarea.getLista();
        if(!consultMini.registerMiniTareas(lista, id)){
            ret=false;
        }
        return ret;
    }
    public void addMiniTarea(){
        if(miniTareasPanel.apunteOptionIsSelected()){
            if(miniTareasPanel.validateNombreField() && miniTareasPanel.validateT1Field()){
                miniTareasPanel.addMiniTarea(new Apunte(miniTareasPanel.getNombreField(),miniTareasPanel.getT1Field(),miniTareasPanel.getCurrentTarea().getId()));
                JOptionPane.showMessageDialog(null, "MiniTarea apunte añadida correctamente");
                miniTareasPanel.incrementCurrentMini();
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese valores válidos por favor");
            }
            
        }else if(miniTareasPanel.enlaceWebOptionIsSelected()){
            if(miniTareasPanel.validateNombreField() && miniTareasPanel.validateT1Field()){
                miniTareasPanel.addMiniTarea(new EnlaceWeb(miniTareasPanel.getNombreField(),miniTareasPanel.getT1Field(),miniTareasPanel.getCurrentTarea().getId()));
                JOptionPane.showMessageDialog(null, "MiniTarea Enlace Web añadida correctamente");
                miniTareasPanel.incrementCurrentMini();
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese valores válidos por favor");
            }
        }else if(miniTareasPanel.instruccionOptionIsSelected()){
            if(miniTareasPanel.validateNombreField() && miniTareasPanel.validateT1Field()){
                miniTareasPanel.addMiniTarea(new Instruccion(miniTareasPanel.getT1Field(),miniTareasPanel.getCurrentTarea().getId()));
                JOptionPane.showMessageDialog(null, "MiniTarea Instruccion añadida correctamente");
                miniTareasPanel.incrementCurrentMini();
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese valores válidos por favor");
            }
        }else if(miniTareasPanel.recordatorioOptionIsSelected()){
            if(miniTareasPanel.validateNombreField() && miniTareasPanel.validateT1Field()){
                miniTareasPanel.addMiniTarea(new Recordatorio(miniTareasPanel.getFecha(),miniTareasPanel.getHora(),miniTareasPanel.getNombreField(),miniTareasPanel.getT1Field(),miniTareasPanel.getCurrentTarea().getId()));
                JOptionPane.showMessageDialog(null, "MiniTarea Instruccion añadida correctamente");
                miniTareasPanel.incrementCurrentMini();
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese valores válidos por favor");
            }
        }
        
        miniTareasPanel.updateMiniTareaTitle();
    }

    private void CreacionAsignatura(ActionEvent a) {
        if(a.getSource()==asignaturaPanel.getCrearButton()){
            if(asignaturaPanel.validarDatos()){
                if(consultAsig.register(asignaturaPanel.getNombre(),asignaturaPanel.getCreditos(),asignaturaPanel.getImportancia())){
                    JOptionPane.showMessageDialog(null, "Asignatura creada correctamente");
                    asignaturaPanel.clean();
                }else{
                    JOptionPane.showMessageDialog(null, "Algo salió mal, verifique los datos e intente nuevamente");
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese valores válidos por favor");
                asignaturaPanel.clean();
            }
        }else{
            asignaturaPanel.clean();
        }
    }
    public Tarea getTareaMasUrgente(){
        
        return consultTareas.getTareaMasProxima();
    }

    
    
   

    
    
    
    
    
}
