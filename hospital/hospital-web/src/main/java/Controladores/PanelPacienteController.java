package Controladores;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.CitaMedicaEJB;
import beans.EJBUsuario;
import entidades.CitaMedica;
import entidades.Medico;
import entidades.Paciente;
import excepciones.ExcepcionNegocio;
import seguridad.SesionBean;

@Named("ppacienteController")
@ViewScoped
public class PanelPacienteController implements Serializable{
	@Inject
	private SesionBean sesion;
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	@EJB
	private CitaMedicaEJB citaMedicaEJB;
	
	private List<CitaMedica> citasPaciente;
	private List<CitaMedica> citasPendientes;
	private List<CitaMedica> citasAtendidas;
	private List<CitaMedica> citasCanceladas;
	
	private Paciente paciente;
	
	private Medico medico;
	
	private String tipoid;
	
	private String numeroid;
	
	@PostConstruct
	public void inicializar(){
		tipoid = sesion.getUsuario().getTipoIdentificacion();
		numeroid = sesion.getUsuario().getNumeroIdentificacion();
		paciente = usuarioEJB.buscarPaciente(tipoid,numeroid);
		if(paciente != null){
			citasPaciente = citaMedicaEJB.citasByPaciente(paciente);
			citasPendientes = citaMedicaEJB.citasByPacienteEstado(paciente, "Pendiente");
			citasAtendidas = citaMedicaEJB.citasByPacienteEstado(paciente, "Atendida");
			citasCanceladas = citaMedicaEJB.citasByPacienteEstado(paciente, "Cancelada");
		}else{
			medico = usuarioEJB.buscarMedico(tipoid, numeroid);
			if(medico != null){
				citasPaciente = citaMedicaEJB.citasByMedico(medico);
				citasPendientes = citaMedicaEJB.citasByMedicoEstado(medico, "Pendiente");
				citasAtendidas = citaMedicaEJB.citasByMedicoEstado(medico, "Atendida");
				citasCanceladas = citaMedicaEJB.citasByMedicoEstado(medico, "Cancelada");
			}
		}
	}
	
	/**
	 * cancelar cita medica
	 */
	public void cancelarCita(CitaMedica cita){
		try{
			cita.setEstado("Cancelada");
			citaMedicaEJB.editar(cita);
			Messages.addFlashGlobalInfo("Se ha cancelado la cita que estaba prevista para la fecha "+cita.getFecha());
			citasPendientes = citaMedicaEJB.citasByPacienteEstado(paciente, "Pendiente");
		}catch(ExcepcionNegocio ex){
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<CitaMedica> getCitasPendientes() {
		return citasPendientes;
	}

	public void setCitasPendientes(List<CitaMedica> citasPendientes) {
		this.citasPendientes = citasPendientes;
	}

	public List<CitaMedica> getCitasAtendidas() {
		return citasAtendidas;
	}

	public void setCitasAtendidas(List<CitaMedica> citasAtendidas) {
		this.citasAtendidas = citasAtendidas;
	}


	public List<CitaMedica> getCitasPaciente() {
		return citasPaciente;
	}

	public void setCitasPaciente(List<CitaMedica> citasPaciente) {
		this.citasPaciente = citasPaciente;
	}


	public List<CitaMedica> getCitasCanceladas() {
		return citasCanceladas;
	}

	public void setCitasCanceladas(List<CitaMedica> citasCanceladas) {
		this.citasCanceladas = citasCanceladas;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getTipoid() {
		return tipoid;
	}

	public void setTipoid(String tipoid) {
		this.tipoid = tipoid;
	}

	public String getNumeroid() {
		return numeroid;
	}

	public void setNumeroid(String numeroid) {
		this.numeroid = numeroid;
	}
}
