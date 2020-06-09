/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Jean
 */
public class Prueba {
    private Long id;
    private Date fecha;
    private boolean positivo;
    private Long idPersonal;

    public Prueba() {
    }

    public Prueba(Date fecha, boolean positivo, Long persona) {
        this.fecha = fecha;
        this.positivo = positivo;
        this.idPersonal = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isPositivo() {
        return positivo;
    }

    public void setPositivo(boolean positivo) {
        this.positivo = positivo;
    }

    public Long getPersona() {
        return idPersonal;
    }

    public void setPersona(Long persona) {
        this.idPersonal = persona;
    }
    
    
}
