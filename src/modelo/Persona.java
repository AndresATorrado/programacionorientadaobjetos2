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
public class Persona {
    private Long idPersona;
    private String documento;
    private String nombre;
    private String direccion;
    private String telefono;
    private String genero;
    private Date fechaNacimiento;
    private ArrayList<Paciente> contactos;

    public Persona() {
    }

    public Persona(String documento, String nombre, String direccion, String genero, Date fechaNacimiento, String telefono) {
        this.documento = documento;
        this.nombre = nombre;
        this.direccion = direccion;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.contactos = new ArrayList<>();
        this.telefono = telefono;
    }

    public Long getId() {
        return idPersona;
    }

    public void setId(Long id) {
        this.idPersona = id;
    }
    
    

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ArrayList<Paciente> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Paciente> contactos) {
        this.contactos = contactos;
    }
    
    
}
