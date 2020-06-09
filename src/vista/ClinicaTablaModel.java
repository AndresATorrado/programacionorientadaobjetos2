/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.ClinicaDao;
import dao.mySQL.DAOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Clinica;

/**
 *
 * @author Jean
 */
public class ClinicaTablaModel extends AbstractTableModel {
    private ClinicaDao clinica;
    private List<Clinica> datos;
    //1 paciente 2 personal 3 persona

    public ClinicaTablaModel(ClinicaDao clinica) {
        this.clinica = clinica;
        datos = new ArrayList<>();
    }

    public void updateModel() throws DAOException {
        datos = clinica.obtenerTodos();

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "id";
            case 1:
                return "nombre";
            case 2:
                return "direccion";
            default : return "";
        }
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clinica preguntado = null;
        preguntado = datos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return preguntado.getId();
            case 1:
                return preguntado.getNombre();
            case 2:
                return preguntado.getDireccion();
            default:
                return "";
        }
    }
}
