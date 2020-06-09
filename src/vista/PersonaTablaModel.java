
package vista;

import dao.PersonaDao;
import dao.mySQL.DAOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Persona;

/**
 *
 * @author Jean
 */
public class PersonaTablaModel extends AbstractTableModel {

    private PersonaDao persona;
    private List<Persona> datos;
    //1 paciente 2 personal 3 persona

    public PersonaTablaModel(PersonaDao persona) {
        this.persona = persona;
        datos = new ArrayList<>();

    }

    public void updateModel() throws DAOException {
        datos = persona.obtenerTodos();

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
        Persona preguntado = null;
        preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getId();
            case 1:
                return preguntado.getNombre();
            case 2:
                DateFormat df = DateFormat.getDateInstance();
                if (preguntado.getFechaNacimiento() != null) {
                    return df.format(preguntado.getFechaNacimiento());
                } else {
                    return "NO AVAIBLE";
                }
            case 3:
                return preguntado.getGenero();
            default:
                return "";
        }
    }

}
