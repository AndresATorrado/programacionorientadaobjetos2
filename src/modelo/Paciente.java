/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Jean
 */
public class Paciente extends Persona{
    private Long idPaciente;
    private long idLugarProcedencia;
    private Date fechaDeteccion;
    private Long idEstado;
    private boolean casa;
    private Long idClinica;

    public Paciente() {
    }

    public Paciente(Long lugarProcedencia, Date fechaDeteccion, Long estado, boolean casa, Long clinica, String documento, String nombre, String direccion, String genero, Date fechaNacimiento, String telefono) {
        super(documento, nombre, direccion, genero, fechaNacimiento, telefono);
        this.idLugarProcedencia = lugarProcedencia;
        this.fechaDeteccion = fechaDeteccion;
        this.idEstado = estado;
        this.casa = casa;
        this.idClinica = clinica;
    }

    public Paciente(long idLugarProcedencia, Date fechaDeteccion, Long idEstado, boolean casa, Long idClinica) {
        this.idLugarProcedencia = idLugarProcedencia;
        this.fechaDeteccion = fechaDeteccion;
        this.idEstado = idEstado;
        this.casa = casa;
        this.idClinica = idClinica;
    }
    
    

    public Long getLugarProcedencia() {
        return idLugarProcedencia;
    }

    public void setLugarProcedencia(Long lugarProcedencia) {
        this.idLugarProcedencia = lugarProcedencia;
    }

    public Date getFechaDeteccion() {
        return fechaDeteccion;
    }

    public void setFechaDeteccion(Date fechaDeteccion) {
        this.fechaDeteccion = fechaDeteccion;
    }

    public Long getEstado() {
        return idEstado;
    }

    public void setEstado(Long estado) {
        this.idEstado = estado;
    }

    public boolean isCasa() {
        return casa;
    }

    public void setCasa(boolean casa) {
        this.casa = casa;
    }

    public Long getClinica() {
        return idClinica;
    }

    public void setClinica(Long clinica) {
        this.idClinica = clinica;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

}
