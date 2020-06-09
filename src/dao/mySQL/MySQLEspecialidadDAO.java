/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mySQL;

import dao.EspecialidadDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Clinica;
import modelo.Especialidad;

/**
 *
 * @author Jean
 */
public class MySQLEspecialidadDAO implements EspecialidadDao {

    final String GETALL = "SELECT idEspecialidad, descripcion FROM especialidad";
    final String GETONE = "SELECT idEspecialidad, descripcion FROM especialidad WHERE idEspecialidad  = ?";
    private Connection conn;

    public MySQLEspecialidadDAO(Connection conn) {
        this.conn = conn;
    }

    private Especialidad convertir(ResultSet rs) throws SQLException {
        switch ((int) rs.getLong("idEspecialidad")) {
            case 1:
                return Especialidad.MEDICO;
            case 2:
                return Especialidad.AUXILIAR;
            case 3:
                return Especialidad.ENFERMERO;
            case 4:
                return Especialidad.ADMINISTRATIVO;
            default:
                return Especialidad.GENERAL;
        }
    }

    @Override
    public void insertar(Especialidad a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Especialidad a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Especialidad a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Especialidad> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Especialidad> especialidades = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                especialidades.add(convertir(rs));
            }
        } catch (Exception e) {
            throw new DAOException("Error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (Exception e) {
                }
            }
        }
        return especialidades;
    }

    @Override
    public Especialidad obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Especialidad es = null;
        try {
            stat = conn.prepareStatement(GETONE);
            System.out.println("id "+id);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                es = convertir(rs);
            } else {
                throw new DAOException("Registro no encontrado");
            }
        } catch (Exception e) {
            throw new DAOException("Error", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (Exception e) {
                }
            }
        }
        return es;
    }

}
