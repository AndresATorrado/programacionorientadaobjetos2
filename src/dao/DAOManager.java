/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Jean
 */
public interface DAOManager {
    
    ClinicaDao getClinicaDAO();
    
    EspecialidadDao getEspecialidadDAO();
    
    EstadoDao getEstadoDAO();
    
    LugarProcedenciaDao getLugarProcedenciaDAO();
    
    PacienteDao getPacienteDAO();
    
    PersonaDao getPersonaDAO();
    
    PersonalSaludDao getPersonaEstudioDAO();
    
    PruebaDao getPruebaDAO();
    
}
