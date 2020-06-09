/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mySQL;

import dao.PersonaDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Paciente;
import modelo.Persona;

/**
 *
 * @author Jean
 */
public class MySQLPersonaDAO implements PersonaDao {

    final String INSERT = "INSERT INTO persona (nombre, direccion, telefono, genero, fecha_nacimiento, documento) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE persona SET nombre = ?, direccion = ?, telefono = ?, genero =  ?, fecha_nacimiento = ?, documento = ? WHERE idPersona = ?";
    final String DELETE = "DELETE FROM persona WHERE idPersona = ?";
    final String GETALL = "SELECT idPersona, nombre, direccion, telefono, genero, fecha_nacimiento, documento FROM persona";
    final String GETONE = "SELECT idPersona, nombre, direccion, telefono, genero, fecha_nacimiento, documento FROM persona WHERE idPersona = ?";

    private Connection conn;

    public MySQLPersonaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Persona a) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getDireccion());
            stat.setString(3, a.getTelefono());
            stat.setString(4, a.getGenero());
            stat.setDate(5, new Date(a.getFechaNacimiento().getTime()));
            stat.setString(6, a.getDocumento());
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
    public void modificar(Persona a) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getNombre());
            stat.setString(2, a.getDireccion());
            stat.setString(3, a.getTelefono());
            stat.setString(4, a.getGenero());
            stat.setDate(5, new Date(a.getFechaNacimiento().getTime()));
            stat.setString(6, a.getDocumento());
            stat.setLong(7, a.getId());
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
    public void eliminar(Persona a) throws DAOException {
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

    private Persona convertir(ResultSet rs) throws SQLException {
        try {
            Date fechaDeteccion = rs.getDate("fecha_nacimiento");
            String nombre = rs.getString("nombre");
            String documento = rs.getString("documento");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono");
            String genero = rs.getString("genero");
            Persona persona = new Persona(documento, nombre, direccion, genero, fechaDeteccion, telefono);
            persona.setId(rs.getLong("idPersona"));
            return persona;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    @Override
    public List<Persona> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Persona> personas = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                personas.add(convertir(rs));
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
        return personas;
    }

    @Override
    public Persona obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Persona p = null;
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
