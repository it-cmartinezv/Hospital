package Controladores;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

import beans.CitaMedicaEJB;
import beans.EJBUsuario;
import entidades.CitaMedica;
import entidades.Paciente;
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
	
	@PostConstruct
	public void inicializar(){
		Paciente paciente = usuarioEJB.buscarPaciente(sesion.getUsuario().getTipoIdentificacion(), sesion.getUsuario().getNumeroIdentificacion());
		if(paciente != null){
			citasPaciente = citaMedicaEJB.citasByPaciente(paciente);
			citasPendientes = citaMedicaEJB.citasByPacienteEstado(paciente, "Pendiente");
			citasAtendidas = citaMedicaEJB.citasByPacienteEstado(paciente, "Atendida");
			citasCanceladas = citaMedicaEJB.citasByPacienteEstado(paciente, "Cancelada");
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
}
