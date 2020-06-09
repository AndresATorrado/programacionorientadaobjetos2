/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mySQL;

import dao.EstadoDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Especialidad;
import modelo.Estado;

/**
 *
 * @author Jean
 */
public class MySQLEstadoDAO implements EstadoDao {

    final String GETALL = "SELECT idEstado, descripcion FROM estado";
    final String GETONE = "SELECT idEstado, descripcion FROM estado WHERE idEstado  = ?";
    private Connection conn;

    public MySQLEstadoDAO(Connection conn) {
        this.conn = conn;
    }

    private Estado convertir(ResultSet rs) throws SQLException {
        switch ((int) rs.getLong("idEstado")) {
            case 1:
                return Estado.SOSPECHOSO;
            case 2:
                return Estado.POSITIVO;
            case 3:
                return Estado.NEGATIVO;
            case 4:
                return Estado.RECUPERADO;
            default:
                return Estado.MUERTO;
        }
    }

    @Override
    public void insertar(Estado a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Estado a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Estado a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estado> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Estado> estados = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                estados.add(convertir(rs));
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
        return estados;
    }

    @Override
    public Estado obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Estado es = null;
        try {
            stat = conn.prepareStatement(GETONE);
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

