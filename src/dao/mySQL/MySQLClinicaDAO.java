/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mySQL;

import dao.ClinicaDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Clinica;

/**
 *
 * @author Jean
 */
public class MySQLClinicaDAO implements ClinicaDao {

    final String INSERT = "INSERT INTO clinica(nombre, direccion) VALUES(? , ?)";
    final String UPDATE = "UPDATE clinica SET nombre = ?, direccion = ? WHERE idClinica = ?";
    final String DELETE = "DELETE FROM clinica WHERE idClinica = ?";
    final String GETALL = "SELECT idClinica, nombre, direccion FROM clinica";
    final String GETONE = "SELECT idClinica, nombre, direccion FROM clinica WHERE idClinica  = ?";

    private Connection conn;

    public MySQLClinicaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Clinica a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getDireccion());
            if (stat.executeUpdate() == 0) {
                System.out.println("Consulta --");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setId(rs.getLong(1));
            } else {
                throw new DAOException("error");
            }
        } catch (SQLException e) {
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
                } catch (SQLException e) {
                    throw new DAOException("Error", e);
                }
            }

        }
    }

    @Override
    public void modificar(Clinica a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getDireccion());
            stat.setLong(3, a.getId());
            if (stat.executeUpdate() == 0) {
                System.out.println("Consulta --");
            }
        } catch (SQLException e) {
            throw new DAOException("Error", e);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("Error", e);
                }
            }

        }
    }

    @Override
    public void eliminar(Clinica a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getId());
            if (stat.executeUpdate() == 0) {
                System.out.println("Consulta --");
            }
        } catch (SQLException e) {
            throw new DAOException("Error", e);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    throw new DAOException("Error", e);
                }
            }

        }
    }

    private Clinica convertir(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre");
        String direccion = rs.getString("direccion");
        Clinica clinica = new Clinica(nombre, direccion);
        clinica.setId(rs.getLong("idClinica"));
        return clinica;
    }

    @Override
    public List<Clinica> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Clinica> clinicas = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                clinicas.add(convertir(rs));
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
        return clinicas;
    }

    @Override
    public Clinica obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Clinica c = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                c = convertir(rs);
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
        return c;
    }

}
