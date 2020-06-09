/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mySQL;

import dao.PersonalSaludDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;
import modelo.PersonalSalud;

/**
 *
 * @author Jean
 */
public class MySQLPersonalSaludDAO implements PersonalSaludDao {

    final String INSERT = "INSERT INTO personal_salud (idPersonal_salud	, fk_especialidad , fk_clinica) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE personal_salud SET fk_especialidad = ?, fk_clinica = ? WHERE idPersonal_salud = ?";
    final String DELETE = "DELETE FROM personal_salud WHERE idPersonal_salud = ?";
    final String GETALL = "SELECT idPersonal_salud, fk_especialidad, fk_clinica FROM personal_salud";
    final String GETONE = "SELECT idPersonal_salud, fk_especialidad, fk_clinica FROM personal_salud WHERE idPersonal_salud = ?";

    private Connection conn;

    public MySQLPersonalSaludDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(PersonalSalud a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setLong(1, a.getId());
            stat.setLong(2, a.getEspecialidad());
            stat.setLong(3, a.getClinica());
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
    public void modificar(PersonalSalud a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setLong(1, a.getEspecialidad());
            stat.setLong(2, a.getClinica());
            stat.setLong(3, a.getIdPersonalSalud());
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
    public void eliminar(PersonalSalud a) throws DAOException {
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

    private PersonalSalud convertir(ResultSet rs) throws SQLException {
        Long especialidad = rs.getLong("fk_especialidad");
        Long clinica = rs.getLong("fk_clinica");
        PersonalSalud personal = new PersonalSalud(especialidad, clinica);
        personal.setId(rs.getLong("idPersonal_salud"));
        personal.setIdPersonalSalud(rs.getLong("idPersonal_salud"));
        return personal;
    }

    @Override
    public List<PersonalSalud> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<PersonalSalud> personal = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                personal.add(convertir(rs));
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
        return personal;
    }

    @Override
    public PersonalSalud obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        PersonalSalud p = null;
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
