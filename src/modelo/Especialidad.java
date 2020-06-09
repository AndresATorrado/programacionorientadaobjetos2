/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jean
 */
public enum Especialidad {
    MEDICO("Medico"),
    ENFERMERO("Enfermero"),
    AUXILIAR("Auxiliar"),
    ADMINISTRATIVO("Administrativo"),
    GENERAL("General");
    
    private String nombre;
    private Long id;
    
    private Especialidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
