/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.PersonaDao;
import dao.PersonalSaludDao;
import dao.PruebaDao;
import dao.mySQL.DAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Prueba;

/**
 *
 * @author Jean
 */
public class PruebaTablaModel extends AbstractTableModel {

    private PruebaDao prueba;
    private PersonalSaludDao personal;
    private PersonaDao persona;
    private List<Prueba> datos;
    //1 paciente 2 personal 3 persona

    public PruebaTablaModel(PruebaDao prueba, PersonalSaludDao personal, PersonaDao persona) {
        this.prueba = prueba;
        this.personal = personal;
        this.persona = persona;
        datos = new ArrayList<>();
    }

    public void updateModel() throws DAOException {
        try {
            datos = prueba.obtenerTodos();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "id";
            case 1:
                return "fecha";
            case 2:
                return "resultado";
            case 3:
                return "personal salud";
            default:
                return "";
        }
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prueba preguntado = null;
        preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getId();
            case 1:
                return preguntado.getFecha();
            case 2:
                return preguntado.isPositivo() ? "positivo" : "negativo";
            case 3: {
                try {
                    return persona.obtener(personal.obtener(preguntado.getPersona()).getId()).getNombre();
                } catch (DAOException ex) {
                    Logger.getLogger(PruebaTablaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            default:
                return "";
        }
    }
}
