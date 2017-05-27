package Controladores;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.CitaMedicaEJB;
import beans.EJBUsuario;
import entidades.CitaMedica;
import entidades.Medico;
import entidades.Paciente;
import seguridad.SesionBean;

@Named("citaController")
@ViewScoped
public class CitaController implements Serializable{
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	@Inject
	private SesionBean sesion;
	
	@EJB
	private CitaMedicaEJB citaEJB;
	
	private int caracter;
	
	private String valoracion;
	
	@Length(max=300,message="Maximo 300 caracteres")
	private String descripcion;
	
	private Medico medico;
	
	private List<Medico> medicos;
	
	private String tipoID;
	private String numeroIdentificacion;
	@PostConstruct
	public void iniciar(){
		medicos = usuarioEJB.listarMedicos();
		tipoID = sesion.getUsuario().getTipoIdentificacion();
		numeroIdentificacion = sesion.getUsuario().getNumeroIdentificacion();
	}

	/**
	 * Registrar cita
	 */
	public void cita(){
		try{
			Paciente paciente = usuarioEJB.buscarPaciente(tipoID, numeroIdentificacion);
			if(paciente != null){
				CitaMedica cita = new CitaMedica();
				cita.setCaracter(caracter);
				cita.setMedico(medico);
				cita.setDescripcion(descripcion);
				cita.setValoracion(valoracion);
				cita.setEstado("Pendiente");
				cita.setPaciente(paciente);
				// Datos de prueba
				Date fecha = new Date();
				Time hora = new Time(22);
				cita.setFecha(fecha);
				cita.setHora(hora);
				// fin datos de prueba
				citaEJB.crear(cita);
				limpiar();
				Messages.addFlashGlobalInfo("Su cita fue registrada exitosamente");
			}else{
				Messages.addFlashGlobalInfo("No eres un paciente");
			}
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * limpiar campos
	 */
	public void limpiar(){
		descripcion = "";
	}
	
	/**
	 * Accesores y Modificadores
	 * 
	 */
	public int getCaracter() {
		return caracter;
	}

	public void setCaracter(int caracter) {
		this.caracter = caracter;
	}

	public String getValoracion() {
		return valoracion;
	}

	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
}
