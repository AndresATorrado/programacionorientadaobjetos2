/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jean
 */
public enum Estado {
    POSITIVO("Positivo"),
    NEGATIVO("Negativo"),
    RECUPERADO("Recuperado"),
    SOSPECHOSO("Sospechoso"),
    MUERTO("Muerto");
    
    private String nombre;
    private Long id;
    
    private Estado(String nombre) {
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
