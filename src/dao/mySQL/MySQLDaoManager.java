/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mySQL;

import dao.ClinicaDao;
import dao.DAOManager;
import dao.EspecialidadDao;
import dao.EstadoDao;
import dao.LugarProcedenciaDao;
import dao.PacienteDao;
import dao.PersonaDao;
import dao.PersonalSaludDao;
import dao.PruebaDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jean
 */
public class MySQLDaoManager implements DAOManager {

    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/covid-db";
    private String user = "root";
    private String password = "";

    private ClinicaDao clinicas = null;
    private EspecialidadDao especialidades = null;
    private EstadoDao estados = null;
    private LugarProcedenciaDao lugars = null;
    private PacienteDao pacientes = null;
    private PersonaDao personas = null;
    private PersonalSaludDao personal = null;
    private PruebaDao pruebas = null;

    public MySQLDaoManager() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }

    @Override
    public ClinicaDao getClinicaDAO() {
        if (clinicas == null) {
            clinicas = new MySQLClinicaDAO(conn);
        }
        return clinicas;
    }

    @Override
    public EspecialidadDao getEspecialidadDAO() {
        if (especialidades == null) {
            especialidades = new MySQLEspecialidadDAO(conn);
        }
        return especialidades;
    }

    @Override
    public EstadoDao getEstadoDAO() {
        if (estados == null) {
            estados = new MySQLEstadoDAO(conn);
        }
        return estados;
    }

    @Override
    public LugarProcedenciaDao getLugarProcedenciaDAO() {
        if (lugars == null) {
            lugars = new MySQLLugarProcedenciaDAO(conn);
        }
        return lugars;
    }

    @Override
    public PacienteDao getPacienteDAO() {
        if (pacientes == null) {
            pacientes = new MySQLPacienteDAO(conn);
        }
        return pacientes;
    }

    @Override
    public PersonaDao getPersonaDAO() {
        if (personas == null) {
            personas = new MySQLPersonaDAO(conn);
        }
        return personas;
    }

    @Override
    public PersonalSaludDao getPersonaEstudioDAO() {
        if (personal == null) {
            personal = new MySQLPersonalSaludDAO(conn);
        }
        return personal;
    }

    @Override
    public PruebaDao getPruebaDAO() {
        if (pruebas == null) {
            pruebas = new MySQLPruebaDAO(conn);
        }
        return pruebas;
    }

}
