/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.PacienteDao;
import dao.mySQL.DAOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jean
 */
public class EstadisticaTablaModel extends AbstractTableModel {
    private PacienteDao paciente;
    private List<Integer> datos;
    //1 paciente 2 personal 3 persona

    public EstadisticaTablaModel(PacienteDao paciente) {
        this.paciente = paciente;
        datos = new ArrayList<>();
    }

    public void updateModel() throws DAOException {
        try {
            datos = paciente.estadisticas();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Sospechosos";
            case 1:
                return "Positivos";
            case 2:
                return "Negativos";
            case 3:
                return "Recuperados";
            case 4:
                return "Muertos";
            default:
                return "";
        }
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return datos.get(0);
            case 1:
                return datos.get(1);
            case 2:
                return datos.get(2);
            case 3: 
                return datos.get(3);
            case 4:
                return datos.get(4);
            default:
                return "";
        }
    }
}
