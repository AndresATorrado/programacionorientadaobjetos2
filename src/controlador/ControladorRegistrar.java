/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.mySQL.DAOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Clinica;
import modelo.Especialidad;
import modelo.Estado;
import modelo.LugarProcedencia;
import modelo.Paciente;
import modelo.Persona;
import modelo.PersonalSalud;
import vista.RegistrarPaciente;
import vista.RegistrarPersona;
import vista.RegistrarPersonal;

/**
 *
 * @author Jean
 */
public class ControladorRegistrar implements ActionListener{

    private RegistrarPersona persona;
    private RegistrarPaciente paciente;
    private RegistrarPersonal personal;
    private Controlador padre;
    private Persona personaA;
    private List<Estado> estados;
    private List<Especialidad> especialidades;
    private List<Clinica> clinicas;
    private List<LugarProcedencia> lugares;
    //0 persona, 1 paciente, 2 personal
    private int tipoRegistro = -1;

    public ControladorRegistrar() {
        persona = new RegistrarPersona();
        paciente  = new RegistrarPaciente();
        personal = new RegistrarPersonal();
        persona.registrar.addActionListener(this);
        paciente.registrar.addActionListener(this);
        personal.registrar.addActionListener(this);
    }
    
    public void iniciar() {
        persona.setVisible(true);
    }
    
    public void iniciarCombos() {
        paciente.estado.removeAllItems();
        for (Estado estado : estados) {
            paciente.estado.addItem(estado.getNombre());
        }
        paciente.clinica.removeAllItems();
        for (Clinica clinica : clinicas) {
            paciente.clinica.addItem(clinica.getNombre());
            personal.clinica.addItem(clinica.getNombre());
        }
        paciente.lugar.removeAllItems();
        for (LugarProcedencia lugar : lugares) {
            paciente.lugar.addItem(lugar.getNombre());
        }
        personal.especialidad.removeAllItems();
        for (Especialidad especialidad : especialidades) {
            personal.especialidad.addItem(especialidad.getNombre());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == persona.registrar) {
            String documento = persona.documento.getText();
            String direccion = persona.direccion.getText();
            String nombre = persona.nombre.getText();
            String genero = persona.genero.getText();
            String telefono= persona.telefono.getText();
            Date fechaN = persona.fechaNacimiento.getDate();
            Persona p = new Persona(documento, nombre, direccion, genero, fechaN, telefono);
            try {
                if (tipoRegistro == 0) {
                    padre.registrarPersona(p);
                } else if (tipoRegistro == 1) {
                    personaA = p;
                    paciente.setVisible(true);
                } else if (tipoRegistro == 2) {
                    personaA = p;
                    personal.setVisible(true);
                }
                persona.dispose();
            } catch (DAOException ex) {
                Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == paciente.registrar) {
            Date fechaN = paciente.fechaDeteccion.getDate();
            Long idEstado = Long.parseLong((paciente.estado.getSelectedIndex()+1)+"");
            boolean casa = paciente.casa.isSelected();
            Long idLugar = Long.parseLong((paciente.lugar.getSelectedIndex()+1)+"");
            Long idClinica = Long.parseLong((paciente.clinica.getSelectedIndex()+1)+"");
            Paciente p = new Paciente(idLugar, fechaN, idEstado, casa, idClinica);
            p.setNombre(personaA.getNombre());
            p.setDocumento(personaA.getDocumento());
            p.setDireccion(personaA.getDireccion());
            p.setTelefono(personaA.getTelefono());
            p.setGenero(personaA.getGenero());
            p.setFechaNacimiento(personaA.getFechaNacimiento());
            try {
                padre.registrarPaciente(p, personaA);
                paciente.dispose();
            } catch (DAOException ex) {
                Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == personal.registrar) {
            Long idEspecialidad = Long.parseLong((personal.especialidad.getSelectedIndex()+1)+"");
            Long idClinica = Long.parseLong((personal.clinica.getSelectedIndex()+1)+"");
            PersonalSalud p = new PersonalSalud(idEspecialidad, idClinica);
            p.setNombre(personaA.getNombre());
            p.setDocumento(personaA.getDocumento());
            p.setDireccion(personaA.getDireccion());
            p.setTelefono(personaA.getTelefono());
            p.setGenero(personaA.getGenero());
            p.setFechaNacimiento(personaA.getFechaNacimiento());
            try {
                padre.registrarPersonal(p, personaA);
                personal.dispose();
            } catch (DAOException ex) {
                Logger.getLogger(ControladorRegistrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Controlador getPadre() {
        return padre;
    }

    public void setPadre(Controlador padre) {
        this.padre = padre;
    }  

    public int getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(int tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public List<Clinica> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<Clinica> clinicas) {
        this.clinicas = clinicas;
    }

    public List<LugarProcedencia> getLugares() {
        return lugares;
    }

    public void setLugares(List<LugarProcedencia> lugares) {
        this.lugares = lugares;
    }
    
    
    
}
