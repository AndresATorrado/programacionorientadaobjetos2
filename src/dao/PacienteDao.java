/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.mySQL.DAOException;
import java.util.List;
import modelo.Paciente;

/**
 *
 * @author Jean
 */
public interface PacienteDao extends DAO<Paciente, Long>{
    public List<Integer> estadisticas() throws DAOException;
}
