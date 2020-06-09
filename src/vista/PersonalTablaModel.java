/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.EspecialidadDao;
import dao.PersonaDao;
import dao.PersonalSaludDao;
import dao.mySQL.DAOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Persona;
import modelo.PersonalSalud;

/**
 *
 * @author Jean
 */
public class PersonalTablaModel extends AbstractTableModel {

    private PersonalSaludDao personal;
    private PersonaDao persona;
    private EspecialidadDao especialidad;
    private List<PersonalSalud> datos;
    //1 paciente 2 personal 3 persona

    public PersonalTablaModel(PersonalSaludDao personal, PersonaDao persona, EspecialidadDao especialidad) {
        this.personal = personal;
        this.persona = persona;
        this.especialidad = especialidad;
        datos = new ArrayList<>();

    }

    public void updateModel() throws DAOException {
        datos = personal.obtenerTodos();

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
                return "especialidad";
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
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PersonalSalud preguntado = null;
        preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getId();
            case 1: {
                try {
                    return persona.obtener(preguntado.getId()).getNombre();
                } catch (DAOException ex) {
                    Logger.getLogger(PersonalTablaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
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

            case 3: {
                try {
                    return persona.obtener(preguntado.getEspecialidad()).getGenero();
                } catch (DAOException ex) {
                    Logger.getLogger(PersonalTablaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case 4: {
                try {
                    return especialidad.obtener(preguntado.getEspecialidad()).getNombre();
                } catch (DAOException ex) {
                    Logger.getLogger(PersonalTablaModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            default:
                return "";
        }
    }
}
