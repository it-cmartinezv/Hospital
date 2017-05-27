package Controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.EJBUsuario;
import entidades.Medico;

@Named("citaController")
@ViewScoped
public class CitaController implements Serializable{
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	private int caracter;
	
	private String valoracion;
	
	@Length(max=300,message="Maximo 300 caracteres")
	private String descripcion;
	
	private Medico medico;
	
	private List<Medico> medicos;
	
	@PostConstruct
	public void iniciar(){
		medicos = usuarioEJB.listarMedicos();
	}

	/**
	 * Registrar cita
	 */
	public void cita(){
		Messages.addFlashGlobalInfo("Su cita fue registrada exitosamente");
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
