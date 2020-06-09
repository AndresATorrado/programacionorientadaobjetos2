/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallercovid;

import controlador.Controlador;
import controlador.ControladorRegistrar;
import dao.DAOManager;
import dao.mySQL.DAOException;
import dao.mySQL.MySQLDaoManager;
import java.sql.SQLException;

/**
 *
 * @author Jean
 */
public class TallerCovid {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, DAOException {
        DAOManager manager = new MySQLDaoManager();
        ControladorRegistrar cr = new ControladorRegistrar();
        Controlador c = new Controlador(manager, cr);
        cr.setPadre(c);
    }
    
}
