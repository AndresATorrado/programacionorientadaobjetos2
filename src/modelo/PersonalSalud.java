/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jean
 */
public class PersonalSalud extends Persona{
    private Long idPersonalSalud;
    private Long idEspecialidad;
    private ArrayList<Prueba> pruebas;
    private Long idClinica;

    public PersonalSalud(Long especialidad, Long clinica, String documento, String nombre, String direccion, String genero, Date fechaNacimiento, String telefono) {
        super(documento, nombre, direccion, genero, fechaNacimiento, telefono);
        this.idEspecialidad = especialidad;
        this.idClinica = clinica;
        this.pruebas = new ArrayList<>();
    }

    public PersonalSalud(Long idEspecialidad, Long idClinica) {
        this.idEspecialidad = idEspecialidad;
        this.idClinica = idClinica;
    }
    
    

    public Long getIdPersonalSalud() {
        return idPersonalSalud;
    }

    public void setIdPersonalSalud(Long idPersonalSalud) {
        this.idPersonalSalud = idPersonalSalud;
    }
    
    

    public Long getEspecialidad() {
        return idEspecialidad;
    }

    public void setEspecialidad(Long especialidad) {
        this.idEspecialidad = especialidad;
    }

    public ArrayList<Prueba> getPruebas() {
        return pruebas;
    }

    public void setPruebas(ArrayList<Prueba> pruebas) {
        this.pruebas = pruebas;
    }

    public Long getClinica() {
        return idClinica;
    }

    public void setClinica(Long clinica) {
        this.idClinica = clinica;
    }
    
    
    
    
}
