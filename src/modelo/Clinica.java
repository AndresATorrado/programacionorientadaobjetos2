/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jean
 */
public class Clinica {
    private Long id;
    private String nombre;
    private String direccion;
    private ArrayList<Paciente> pacientes;
    private ArrayList<PersonalSalud> personal;

    public Clinica() {
    }

    public Clinica(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.pacientes = new ArrayList<>();
        this.personal = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(ArrayList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<PersonalSalud> getPersonal() {
        return personal;
    }

    public void setPersonal(ArrayList<PersonalSalud> personal) {
        this.personal = personal;
    }
    
    
}
