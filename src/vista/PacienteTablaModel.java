/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.EstadoDao;
import dao.PacienteDao;
import dao.PersonaDao;
import dao.mySQL.DAOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Paciente;
import modelo.Persona;

/**
 *
 * @author Jean
 */
public class PacienteTablaModel extends AbstractTableModel {

    private PacienteDao paciente;
    private PersonaDao persona;
    private EstadoDao estado;
    private List<Paciente> datos;
    //1 paciente 2 personal 3 persona

    public PacienteTablaModel(PacienteDao paciente, PersonaDao persona, EstadoDao estado) {
        this.paciente = paciente;
        this.persona = persona;
        this.estado = estado;
        datos = new ArrayList<>();
    }

    public void updateModel() throws DAOException {
        datos = paciente.obtenerTodos();

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "id";
            case 1:
                return "nombre";
            case 2:
                return "fecha de nacimiento";
            case 3:
                return "género";
            case 4:
                return "Estado";
            default : return "";
        }
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Paciente preguntado = null;
        preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getId();
            case 1:
                try {
                    return persona.obtener(preguntado.getId()).getNombre();
                } catch (DAOException ex) {
                    Logger.getLogger(PacienteTablaModel.class.getName()).log(Level.SEVERE, null, ex);
                }

            case 2:
                DateFormat df = DateFormat.getDateInstance();
                try {
                    if (persona.obtener(preguntado.getId()).getFechaNacimiento() != null) {
                        return df.format(persona.obtener(preguntado.getId()).getFechaNacimiento());
                    } else {
                        return "NO AVAIBLE";
                    }
                } catch (DAOException ex) {
                    Logger.getLogger(PacienteTablaModel.class.getName()).log(Level.SEVERE, null, ex);
                }

            case 3:
        {
            try {
                return persona.obtener(preguntado.getId()).getGenero();
            } catch (DAOException ex) {
                Logger.getLogger(PacienteTablaModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            case 4:
        {
            try {
                return estado.obtener(preguntado.getEstado()).getNombre();
            } catch (DAOException ex) {
                Logger.getLogger(PacienteTablaModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            default:
                return "";
        }
    }
}
