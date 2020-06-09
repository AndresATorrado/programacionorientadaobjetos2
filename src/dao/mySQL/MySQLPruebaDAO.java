/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mySQL;

import dao.PruebaDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Paciente;
import modelo.Prueba;

/**
 *
 * @author Jean
 */
public class MySQLPruebaDAO implements PruebaDao {

    final String INSERT = "INSERT INTO prueba (fecha, positivo, fk_personal_salud) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE prueba SET fecha = ?, positivo = ?, fk_personal_salud = ? WHERE idPrueba = ?";
    final String DELETE = "DELETE FROM prueba WHERE idPrueba = ?";
    final String GETALL = "SELECT idPrueba, fecha, positivo, fk_personal_salud FROM prueba";
    final String GETONE = "SELECT idPrueba, fecha, positivo, fk_personal_salud FROM prueba WHERE idPrueba = ?";

    private Connection conn;

    public MySQLPruebaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Prueba a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setDate(1, new Date(a.getFecha().getTime()));
            byte positivo = a.isPositivo() ? (byte) 1 : 0;
            stat.setByte(2, positivo);
            stat.setLong(3, a.getPersona());
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
    public void modificar(Prueba a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setDate(1, new Date(a.getFecha().getTime()));
            byte positivo = a.isPositivo() ? (byte) 1 : 0;
            stat.setByte(2, positivo);
            stat.setLong(3, a.getPersona());
            stat.setLong(4, a.getId());
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
    public void eliminar(Prueba a) throws DAOException {
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

    private Prueba convertir(ResultSet rs) throws SQLException {
        Date fecha = rs.getDate("fecha");
        boolean positivo = rs.getByte("positivo") == 1 ? true : false;
        Long personal = rs.getLong("fk_personal_salud");
        Prueba prueba = new Prueba(fecha, positivo, personal);
        prueba.setId(rs.getLong("idPrueba"));
        return prueba;
    }

    @Override
    public List<Prueba> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Prueba> pruebas = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                pruebas.add(convertir(rs));
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
        return pruebas;
    }

    @Override
    public Prueba obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Prueba p = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                p = convertir(rs);
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
        return p;
    }

}
