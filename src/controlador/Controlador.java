/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClinicaDao;
import dao.DAOManager;
import dao.EspecialidadDao;
import dao.EstadoDao;
import dao.LugarProcedenciaDao;
import dao.PacienteDao;
import dao.PersonaDao;
import dao.PersonalSaludDao;
import dao.PruebaDao;
import dao.mySQL.DAOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Clinica;
import modelo.Paciente;
import modelo.Persona;
import modelo.PersonalSalud;
import modelo.Prueba;
import vista.ClinicaTablaModel;
import vista.EstadisticaTablaModel;
import vista.Index;
import vista.PacienteTablaModel;
import vista.PersonaTablaModel;
import vista.PersonalTablaModel;
import vista.PruebaTablaModel;
import vista.RegistrarClinica;
import vista.RegistrarPrueba;

/**
 *
 * @author Jean
 */
public class Controlador implements ActionListener {

    private DAOManager manager;
    private PersonalSaludDao personalDao;
    private PersonaDao personaDao;
    private PacienteDao pacienteDao;
    private EstadoDao estadoDao;
    private EspecialidadDao especialidadDao;
    private PruebaDao pruebaDao;
    private PersonaTablaModel modelPersona;
    private PacienteTablaModel modelPaciente;
    private PersonalTablaModel modelPersonal;
    private PruebaTablaModel modelPrueba;
    private ClinicaTablaModel modelClinica;
    private EstadisticaTablaModel modelestadisticas;
    private ClinicaDao clinicaDao;
    private LugarProcedenciaDao lugarDao;
    private ControladorRegistrar cRegistrar;
    private Index index;
    private RegistrarClinica rClinica;
    private RegistrarPrueba rPrueba;

    public Controlador(DAOManager manager, ControladorRegistrar cr) throws DAOException {
        this.manager = manager;
        cRegistrar = cr;
        personalDao = this.manager.getPersonaEstudioDAO();
        pacienteDao = this.manager.getPacienteDAO();
        personaDao = this.manager.getPersonaDAO();
        clinicaDao = this.manager.getClinicaDAO();
        pruebaDao = this.manager.getPruebaDAO();
        especialidadDao = this.manager.getEspecialidadDAO();
        lugarDao = this.manager.getLugarProcedenciaDAO();
        estadoDao = this.manager.getEstadoDAO();
        modelPersona = new PersonaTablaModel(personaDao);
        modelPaciente = new PacienteTablaModel(pacienteDao, personaDao, estadoDao);
        modelPersonal = new PersonalTablaModel(personalDao, personaDao, especialidadDao);
        modelClinica = new ClinicaTablaModel(clinicaDao);
        modelPrueba = new PruebaTablaModel(pruebaDao, personalDao, personaDao);
        modelestadisticas = new EstadisticaTablaModel(pacienteDao);
        index = new Index();
        rClinica = new RegistrarClinica();
        rPrueba = new RegistrarPrueba();
        index.setVisible(true);
        iniciarBotones();
        cRegistrar.setEstados(estadoDao.obtenerTodos());
        cRegistrar.setEspecialidades(especialidadDao.obtenerTodos());
        cRegistrar.setClinicas(clinicaDao.obtenerTodos());
        cRegistrar.setLugares(lugarDao.obtenerTodos());
        cRegistrar.iniciarCombos();
        iniciarTabla();
        iniciarCombos();
    }

    public void iniciarBotones() {
        index.btnPacientes.addActionListener(this);
        index.btnPersonal.addActionListener(this);
        index.btnPersonas.addActionListener(this);
        index.btnClinicas.addActionListener(this);
        index.btnPruebas.addActionListener(this);
        index.btnRegistrarPersona.addActionListener(this);
        index.btnRegistrarPaciente.addActionListener(this);
        index.btnRegistrarPersonal.addActionListener(this);
        index.btnRegistrarClinica.addActionListener(this);
        index.btnRegistrarPrueba.addActionListener(this);
        rPrueba.registrar.addActionListener(this);
        index.btnPacientes.addActionListener(this);
        index.btnPersonal.addActionListener(this);
        index.btnEstadisticas.addActionListener(this);
    }

    public void registrarPersona(Persona p) throws DAOException {
        personaDao.insertar(p);
        JOptionPane.showMessageDialog(null, "Registro éxitoso " + p.getId());
    }

    public void registrarPaciente(Paciente p, Persona pe) throws DAOException {
        personaDao.insertar(pe);
        p.setIdPaciente(pe.getId());
        pacienteDao.insertar(p);
        JOptionPane.showMessageDialog(null, "Registro éxitoso");
    }

    public void registrarPersonal(PersonalSalud p, Persona pe) throws DAOException {
        personaDao.insertar(pe);
        p.setIdPersonalSalud(pe.getId());
        p.setId(pe.getId());
        personalDao.insertar(p);
        JOptionPane.showMessageDialog(null, "Registro éxitoso");
    }

    public void registrarClinica() {
        String nombre = rClinica.nombre.getText();
        String direccion = rClinica.direccion.getText();
        Clinica a = new Clinica(nombre, direccion);
        try {
            clinicaDao.insertar(a);
            rClinica.dispose();
        } catch (DAOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrarPrueba(Prueba p) throws DAOException {
        pruebaDao.insertar(p);
        JOptionPane.showMessageDialog(null, "Registro éxitoso");
        iniciarCombos();
    }

    public void iniciarTabla() throws DAOException {
        modelPersona.updateModel();
        index.tabla.setModel(modelPersona);
    }

    public void iniciarCombos() throws DAOException {
        rPrueba.personal.removeAllItems();
        for (PersonalSalud personal : personalDao.obtenerTodos()) {
            rPrueba.personal.addItem(personal.getId() + "-" + personaDao.obtener(personal.getId()).getNombre());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == index.btnPacientes) {
            try {
                modelPaciente.updateModel();
                index.tabla.setModel(modelPaciente);
            } catch (DAOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == index.btnPersonal) {
            try {
                modelPersonal.updateModel();
                index.tabla.setModel(modelPersonal);
            } catch (DAOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == index.btnPersonas) {
            try {
                modelPersona.updateModel();
                index.tabla.setModel(modelPersona);
            } catch (DAOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == index.btnClinicas) {
            try {
                modelClinica.updateModel();
                index.tabla.setModel(modelClinica);
            } catch (DAOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == index.btnPruebas) {
            try {
                modelPrueba.updateModel();
                index.tabla.setModel(modelPrueba);
            } catch (DAOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == index.btnRegistrarPersona) {
            cRegistrar.setTipoRegistro(0);
            cRegistrar.iniciar();
        } else if (e.getSource() == index.btnRegistrarPaciente) {
            cRegistrar.setTipoRegistro(1);
            cRegistrar.iniciar();
        } else if (e.getSource() == index.btnRegistrarPersonal) {
            cRegistrar.setTipoRegistro(2);
            cRegistrar.iniciar();
        } else if (e.getSource() == index.btnRegistrarClinica) {
            rClinica.setVisible(true);
        } else if (e.getSource() == rClinica.btnRegistrarClinica) {
            registrarClinica();
            rClinica.dispose();
        } else if (e.getSource() == index.btnRegistrarPrueba) {
            rPrueba.setVisible(true);
        } else if (e.getSource() == rPrueba.registrar) {
            Date fecha = rPrueba.fecha.getDate();
            boolean positivo = rPrueba.positivo.isSelected();
            String item = (String) rPrueba.personal.getSelectedItem();
            String[] id = item.split("-");
            Long idPersona = Long.parseLong(id[0]);
            Prueba p = new Prueba(fecha, positivo, idPersona);
            try {
                registrarPrueba(p);
            } catch (DAOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            rPrueba.dispose();
        } else if (e.getSource() == index.btnEstadisticas) {
            try {
                modelestadisticas.updateModel();
                index.tabla.setModel(modelestadisticas);
            } catch (DAOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cRegistrar.iniciarCombos();
    }
}
