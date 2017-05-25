package Controladores;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import beans.EJBUsuario;
import beans.EPSEJB;
import beans.LocalizacionEJB;
import entidades.Ciudad;
import entidades.Departamento;
import entidades.Medico;
import entidades.Pais;
import excepciones.ExcepcionNegocio;
import seguridad.Rol;

import javax.inject.Named;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Carlos Alfredo Martinez Villada
 * Clase que controla la ventana de medico 
 * 
 */

@Named("medicoController")
@ViewScoped
public class MedicoController implements Serializable{
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	@EJB
	private LocalizacionEJB localizacionEJB;
	
	@EJB
	private EPSEJB epsEJB;
	
	String tipoID;
	
	@Pattern(regexp="[0-9]*",message="Solo numeros")
	String numeroIdentificacion;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	String nombre;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Apellido No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	String apellido;
	
	String genero;

	Date fechaNacimiento;
	
	@Email
	String correo;
	
	@Length(min=4,max=200,message="longitud entre 4 y 200")
	String password;
	
	@Pattern(regexp="[0-9]*",message="Solo numeros")
	@Length(min=5,max=20,message="longitud entre 5 y 20")
	String telefono;
	
	private Pais pais;
	private Departamento departamento;
	private Ciudad ciudad;
	private String tarjetaProfesional;
	private int disponible;
	
	private List<Pais> paises;
	private List<Departamento> departamentos;
	private List<Ciudad> ciudades;
	private List<Medico> medicos;
	@PostConstruct
	public void inicializar(){
		try{
			paises = localizacionEJB.listarPaises();
			medicos = usuarioEJB.listarMedicos();
			if(!paises.isEmpty()){
				departamentos = localizacionEJB.departamentosByPais(paises.get(0));
				if(!departamentos.isEmpty()){
					ciudades = localizacionEJB.ciudadesByDepartamento(departamentos.get(0));
				}
			}
			//listaEps = epsEJB.listar();
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Registrar
	 * 
	 */
	public void registrar(){
		try{
			Medico medico = new Medico();
			medico.setNombre(nombre);
			medico.setApellido(apellido);
			medico.setCorreo(correo);
			medico.setGenero(genero);
			medico.setCiudad(ciudad);
			medico.setFechaNacimiento(fechaNacimiento);
			medico.setNumeroIdentificacion(numeroIdentificacion);
			medico.setTipoIdentificacion(tipoID);
			medico.setTelefono(telefono);
			medico.setPassword(password);
			if(disponible == 0){
				medico.setEstado(true);
			}else{
				medico.setEstado(false);
			}
			medico.setTarjetaProfesional(tarjetaProfesional);
			usuarioEJB.registrarMedico(medico);
			limpiar();
			Messages.addFlashGlobalInfo("Medico "+nombre+" "+apellido+" registrado exitosamente");

		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Buscar
	 * 
	 */
	public void buscar(){
		try{
			if(tarjetaProfesional != null){
				//Medico medico = usuarioEJB.buscarMedico(tipoID, numeroIdentificacion);
				Medico medico = usuarioEJB.buscarMedicoByTarjeta(tarjetaProfesional);
				if(medico != null){
					nombre = medico.getNombre();
					apellido = medico.getApellido();
					correo = medico.getCorreo();
					telefono = medico.getTelefono();
					password = medico.getPassword();
					numeroIdentificacion = medico.getNumeroIdentificacion();
					tarjetaProfesional = medico.getTarjetaProfesional();
					pais = medico.getCiudad().getDepartamento().getPais();
					departamento = medico.getCiudad().getDepartamento();
					ciudad = medico.getCiudad();
					fechaNacimiento = medico.getFechaNacimiento();
					tipoID = medico.getTipoIdentificacion();
					if(medico.isEstado()){
						disponible = 0;
					}else{
						disponible = 1;
					}
					password = medico.getPassword();
					
				}else{
					Messages.addGlobalError("No se ha encontrado ningun medico con este numero de tarjeta profesional");
				}
			}else{
				Messages.addGlobalError("Por favor ingrese el numero de tarjeta profesional del medico a buscar");
			}
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void limpiar(){
		nombre = "";
		apellido = "";
		correo = "";
		telefono = "";
		password = "";
		numeroIdentificacion = "";
		tarjetaProfesional = "";
		fechaNacimiento = null;
	}
	
	/**
	 * Registrar
	 * 
	 */
	public void editar(){
		try{
			Medico medico = usuarioEJB.buscarMedicoByTarjeta(tarjetaProfesional);
			if(medico != null){
				medico.setNombre(nombre);
				medico.setApellido(apellido);
				medico.setCorreo(correo);
				medico.setGenero(genero);
				medico.setCiudad(ciudad);
				medico.setFechaNacimiento(fechaNacimiento);
				medico.setNumeroIdentificacion(numeroIdentificacion);
				medico.setTipoIdentificacion(tipoID);
				medico.setTelefono(telefono);
				medico.setPassword(password);
				if(disponible == 0){
					medico.setEstado(true);
				}else{
					medico.setEstado(false);
				}
				usuarioEJB.editarMedico(medico);
				limpiar();
				Messages.addFlashGlobalInfo("El Medico "+nombre+" "+apellido+" se ha actualizado exitosamente");
			}else{
				Messages.addGlobalError("Por favor ingrese el numero de tarjeta profesional del medico a buscar");
			}
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Eliminar
	 */
	public void eliminar(){
		try{
			usuarioEJB.eliminarMedico(tarjetaProfesional);
			limpiar();
			Messages.addGlobalError("Se ha eliminado correctamente");
		}catch(ExcepcionNegocio e){
			Messages.addGlobalError(e.getMessage());
		}
	}
	/**
	 * Listar departamentos de un respectivo pais
	 */
	public void departamentosByPais(){
		try{
			if(pais != null){
				System.out.println("EL PAIS SELECCIONADO ES:"+pais.getNombre());
				departamentos = localizacionEJB.departamentosByPais(pais);
				if(!departamentos.isEmpty()){
					departamento = departamentos.get(0);
					ciudadesBydepartamento();
				}else{
					ciudades.clear();
				}
			}
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	/**
	 * Listar ciudades de un respectivo departamento
	 */
	public void ciudadesBydepartamento(){
		try{
			if(departamento != null){
				System.out.println("EL DEPARTAMENTO SELECCIONADO ES:"+departamento.getNombre());
				ciudades = localizacionEJB.ciudadesByDepartamento(departamento);
			}
		}catch (excepciones.ExcepcionNegocio e){
			Messages.addFlashGlobalError(e.getMessage());
		}
	}
	
	
	
	/**
	 * Calendario
	 */
	
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
	
	/**
	 * Accesores y Modificadores
	 *
	 */
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoID() {
		return tipoID;
	}

	public void setTipoID(String tipoID) {
		this.tipoID = tipoID;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTarjetaProfesional() {
		return tarjetaProfesional;
	}

	public void setTarjetaProfesional(String tarjetaProfesional) {
		this.tarjetaProfesional = tarjetaProfesional;
	}

	public int getDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
}