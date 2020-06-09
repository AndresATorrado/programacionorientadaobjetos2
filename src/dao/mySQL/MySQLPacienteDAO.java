/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mySQL;

import dao.PacienteDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Clinica;
import modelo.Paciente;

/**
 *
 * @author Jean
 */
public class MySQLPacienteDAO implements PacienteDao {

    final String INSERT = "INSERT INTO paciente(idPaciente, fecha_Deteccion, fk_estado, casa, fk_lugar, fk_clinica) VALUES(? , ?, ?, ?, ?,  ?)";
    final String UPDATE = "UPDATE paciente SET fecha_Deteccion = ?, fk_estado = ?, casa = ?, fk_lugar =  ?, fk_clinica = ? WHERE idPaciente = ?";
    final String DELETE = "DELETE FROM paciente WHERE idPaciente = ?";
    final String GETALL = "SELECT idPaciente, fecha_Deteccion, fk_estado, casa, fk_lugar, fk_clinica FROM paciente";
    final String GETONE = "SELECT idPaciente, fecha_Deteccion, fk_estado, casa, fk_lugar, fk_clinica FROM paciente WHERE idPaciente = ?";
    final String GETSTATE = "SELECT COUNT(*) FROM paciente WHERE fk_estado = ?";

    private Connection conn;

    public MySQLPacienteDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Paciente a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setLong(1, a.getIdPaciente());
            stat.setDate(2, new Date(a.getFechaDeteccion().getTime()));
            stat.setLong(3, a.getEstado());
            byte casa = a.isCasa() ? (byte) 1 : 0;
            stat.setByte(4, casa);
            stat.setLong(5, a.getLugarProcedencia());
            stat.setLong(6, a.getClinica());
            if (stat.executeUpdate() == 0) {
                System.out.println("Consulta --");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                a.setIdPaciente(rs.getLong(1));
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
    public void modificar(Paciente a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setDate(1, new Date(a.getFechaDeteccion().getTime()));
            stat.setLong(2, a.getEstado());
            byte casa = a.isCasa() ? (byte) 1 : 0;
            stat.setByte(3, casa);
            stat.setLong(4, a.getLugarProcedencia());
            stat.setLong(5, a.getClinica());
            stat.setLong(6, a.getId());
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
    public void eliminar(Paciente a) throws DAOException {
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

    private Paciente convertir(ResultSet rs) throws SQLException {
        Date fechaDeteccion = rs.getDate("fecha_Deteccion");
        Long estado = rs.getLong("fk_estado");
        boolean casa = rs.getByte("casa") == 1 ? true : false;
        Long lugar = rs.getLong("fk_lugar");
        Long clinica = rs.getLong("fk_clinica");
        Paciente paciente = new Paciente(lugar, fechaDeteccion, estado, casa, clinica);
        paciente.setId(rs.getLong("idPaciente"));
        paciente.setIdPaciente(rs.getLong("idPaciente"));
        return paciente;
    }

    @Override
    public List<Paciente> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                pacientes.add(convertir(rs));
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
        return pacientes;
    }

    @Override
    public Paciente obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Paciente p = null;
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

    /**
     *
     * @return @throws DAOException
     */
    @Override
    public List<Integer> estadisticas() {
        List<Integer> datos = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(GETSTATE);
            for (int i = 1; i <= 5; i++) {
                stat.setLong(1, i);
                rs = stat.executeQuery();
                int size = 0;
                while (rs.next()) {
                    size = Integer.parseInt(rs.getString("count(*)"));
                    System.out.println(Integer.parseInt(rs.getString("count(*)")));
                }
                datos.add(size);
            }
        } catch (SQLException e) {
            try {
                throw new DAOException("Error", e);
            } catch (DAOException ex) {
                Logger.getLogger(MySQLPacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        return datos;
    }

}
