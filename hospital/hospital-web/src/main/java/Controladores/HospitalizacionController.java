package Controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.CamaEJB;
import beans.EJBUsuario;
import beans.HospitalizacionEJB;
import entidades.Cama;
import entidades.Hospitalizacion;
import entidades.Medico;
import entidades.Persona;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@ViewScoped
@Named("hospitalizacionController")
public class HospitalizacionController implements Serializable{

	@EJB
	private HospitalizacionEJB hospitalizacionEJB;
	
	@EJB
	private CamaEJB camaEJB;
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	Date fechaEntrada;
	
	Date fechaSalida;
	
	private Cama cama;
	
	private Medico medico;
	
	private List<Cama> camas;
	
	private List<Medico> medicos;
	
	private List<Hospitalizacion> hospitalizaciones;
	
	@PostConstruct
	public void inicializar(){
		try{
			camas = camaEJB.listarCamas();
			medicos = usuarioEJB.listarMedicos();
			
		}catch(excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public void registrar(){
		try{
			Hospitalizacion hospitalizacion = new Hospitalizacion();
			hospitalizacion.setEntrada(fechaEntrada);
			hospitalizacion.setSalida(fechaSalida);
			hospitalizacion.setCama(cama);
			hospitalizacion.setMedico(medico);
			hospitalizacionEJB.crear(hospitalizacion);
			Messages.addFlashGlobalInfo("Hospitalizacion registrada exitosamente");
			
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	public void limpiar(){
		
		
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Cama getCama() {
		return cama;
	}

	public void setCama(Cama cama) {
		this.cama = cama;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Cama> getCamas() {
		return camas;
	}

	public void setCamas(List<Cama> camas) {
		this.camas = camas;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
	
	
	
	
	
	
}
