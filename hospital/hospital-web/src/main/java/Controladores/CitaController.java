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
import beans.CitaSintomaEJB;
import beans.EJBUsuario;
import beans.SintomaEJB;
import entidades.CitaMedica;
import entidades.CitaSintoma;
import entidades.Medico;
import entidades.Paciente;
import entidades.Sintoma;
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
	
	@EJB
	private CitaSintomaEJB citaSintomaEJB;
	
	@EJB
	private SintomaEJB sintomaEJB;
	
	private int id;
	
	private int caracter;
	
	private String valoracion;
	
	@Length(max=300,message="Maximo 300 caracteres")
	private String descripcion;
	
	private Medico medico;
	
	private List<Medico> medicos;
	
	private Sintoma sintoma;
	
	private List<Sintoma> sintomas;
	
	private List<CitaSintoma> citaSintomas;
	
	private String tipoID;
	private String numeroIdentificacion;
	
	private CitaMedica cita;
	@PostConstruct
	public void iniciar(){
		medicos = usuarioEJB.listarMedicos();
		tipoID = sesion.getUsuario().getTipoIdentificacion();
		numeroIdentificacion = sesion.getUsuario().getNumeroIdentificacion();
		sintomas = sintomaEJB.listarSintoma();
	}

	/**
	 * Registrar cita
	 */
	public void pedirCita(){
		try{
			Paciente paciente = usuarioEJB.buscarPaciente(tipoID, numeroIdentificacion);
			if(paciente != null){
				cita = new CitaMedica();
				cita.setCaracter(caracter);
				cita.setMedico(medico);
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
				Messages.addFlashGlobalInfo("Su cita fue registrada exitosamente, puedes agregar sintomas a la cita para dar indicaciones al medico.");
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
	 * Agregar sintoma
	 */
	public void addSintoma(){
		try{
			if(cita != null){
				CitaSintoma citaSintoma = new CitaSintoma();
				citaSintoma.setCita(cita);
				citaSintoma.setSintoma(sintoma);
				citaSintomaEJB.crear(citaSintoma);
				citaSintomas();
				Messages.addFlashGlobalInfo("Agregaste el sintoma "+sintoma.getNombre()+" correctamente");
			}else{
				Messages.addFlashGlobalInfo("Pida la cita primero");
			}
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * listar sintomas de una cita
	 */
	public void citaSintomas(){
		citaSintomas = citaSintomaEJB.listarSintomasByCita(cita);
	}
		
	/**
	 * Buscar cita
	 */
	public void buscar(){
		Paciente paciente = usuarioEJB.buscarPaciente(tipoID, numeroIdentificacion);
		CitaMedica lacita = new CitaMedica();
		if(paciente != null){
			lacita = citaEJB.citasByPacienteId(id, paciente);
		}else{
			lacita = citaEJB.buscar(id);
		}
		if(lacita != null){
			caracter = lacita.getCaracter();
			valoracion = lacita.getValoracion();
			medico = lacita.getMedico();
			descripcion = lacita.getDescripcion();
			cita = lacita;
			citaSintomas();
		}else{
			limpiar();
			sintomas = null;
			Messages.addFlashGlobalInfo("No se ha encontrado ninguna cita con este codigo");
		}
	}
	
	/**
	 * Buscar cita
	 */
	public void buscarCitaMedico(){
		Medico medico = usuarioEJB.buscarMedico(tipoID, numeroIdentificacion);
		CitaMedica lacita = new CitaMedica();
		if(medico != null){
			lacita = citaEJB.citasByMedicoId(id, medico);
		}else{
			lacita = citaEJB.buscar(id);
		}
		if(lacita != null){
			caracter = lacita.getCaracter();
			valoracion = lacita.getValoracion();
			medico = lacita.getMedico();
			descripcion = lacita.getDescripcion();
			cita = lacita;
			citaSintomas();
		}else{
			limpiar();
			sintomas = null;
			Messages.addFlashGlobalInfo("No se ha encontrado ninguna cita con este codigo");
		}
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

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	public List<Sintoma> getSintomas() {
		return sintomas;
	}

	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}

	public List<CitaSintoma> getCitaSintomas() {
		return citaSintomas;
	}

	public void setCitaSintomas(List<CitaSintoma> citaSintomas) {
		this.citaSintomas = citaSintomas;
	}

	public CitaMedica getCita() {
		return cita;
	}

	public void setCita(CitaMedica cita) {
		this.cita = cita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
