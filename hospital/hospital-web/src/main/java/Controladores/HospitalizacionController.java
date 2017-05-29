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
	
	int id;
	
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
			hospitalizaciones = hospitalizacionEJB.listarHospitalizaciones();
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
			hospitalizaciones = hospitalizacionEJB.listarHospitalizaciones();
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Buscar hospitalizacion
	 */
	public void buscar(){
		try{
			Hospitalizacion hospitalizacion = hospitalizacionEJB.buscar(id);
			if(hospitalizacion != null){
				fechaEntrada = hospitalizacion.getEntrada();
				fechaSalida = hospitalizacion.getSalida();
				cama = hospitalizacion.getCama();
				medico = hospitalizacion.getMedico();
			}else{
				Messages.addFlashGlobalError("No se ha encontrado ninguna hospitalizacion");
			}
		}catch(ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
			
		}
	}
	
	/**
	 * Metodo para editar una hospitalizacion
	 */
	public void editar(){
		try{
			Hospitalizacion hospitalizacion = hospitalizacionEJB.buscar(id);
			if(hospitalizacion != null){
				hospitalizacion.setEntrada(fechaEntrada);
				hospitalizacion.setSalida(fechaSalida);
				hospitalizacion.setCama(cama);
				hospitalizacion.setMedico(medico);
				hospitalizacionEJB.editar(hospitalizacion);
				Messages.addFlashGlobalInfo("La hospitalizacion se ha actualizado correctamente");
			}else{
				Messages.addGlobalError("No se ha encontrado ninguna hospitalizacion para actualizar");
			}
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
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

	public List<Hospitalizacion> getHospitalizaciones() {
		return hospitalizaciones;
	}

	public void setHospitalizaciones(List<Hospitalizacion> hospitalizaciones) {
		this.hospitalizaciones = hospitalizaciones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	
}
