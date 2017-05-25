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
import entidades.Farmaceutico;
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
 * Clase que controla la ventana de Farmaceutico 
 * 
 */

@Named("farmaceuticoController")
@ViewScoped
public class FarmaceuticoController implements Serializable{
	
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
	
	private List<Pais> paises;
	private List<Departamento> departamentos;
	private List<Ciudad> ciudades;
	private List<Farmaceutico> farmaceuticos;
	@PostConstruct
	public void inicializar(){
		try{
			paises = localizacionEJB.listarPaises();
			farmaceuticos = usuarioEJB.listarFarmaceuticos();
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
			Farmaceutico farmaceutico = new Farmaceutico();
			farmaceutico.setNombre(nombre);
			farmaceutico.setApellido(apellido);
			farmaceutico.setCorreo(correo);
			farmaceutico.setGenero(genero);
			farmaceutico.setCiudad(ciudad);
			farmaceutico.setFechaNacimiento(fechaNacimiento);
			farmaceutico.setNumeroIdentificacion(numeroIdentificacion);
			farmaceutico.setTipoIdentificacion(tipoID);
			farmaceutico.setTelefono(telefono);
			farmaceutico.setPassword(password);
			farmaceutico.setTarjetaProfesional(tarjetaProfesional);
			usuarioEJB.registrarFarmaceutico(farmaceutico);
			limpiar();
			Messages.addFlashGlobalInfo("Farmaceutico "+nombre+" "+apellido+" registrado exitosamente");

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
				//Farmaceutico Farmaceutico = usuarioEJB.buscarFarmaceutico(tipoID, numeroIdentificacion);
				Farmaceutico farmaceutico = usuarioEJB.buscarFarmaceuticoByTarjeta(tarjetaProfesional);
				if(farmaceutico != null){
					nombre = farmaceutico.getNombre();
					apellido = farmaceutico.getApellido();
					correo = farmaceutico.getCorreo();
					telefono = farmaceutico.getTelefono();
					password = farmaceutico.getPassword();
					numeroIdentificacion = farmaceutico.getNumeroIdentificacion();
					tarjetaProfesional = farmaceutico.getTarjetaProfesional();
					pais = farmaceutico.getCiudad().getDepartamento().getPais();
					departamento = farmaceutico.getCiudad().getDepartamento();
					ciudad = farmaceutico.getCiudad();
					fechaNacimiento = farmaceutico.getFechaNacimiento();
					tipoID = farmaceutico.getTipoIdentificacion();
					password = farmaceutico.getPassword();
				}else{
					Messages.addGlobalError("No se ha encontrado ningun Farmaceutico con este numero de tarjeta profesional");
				}
			}else{
				Messages.addGlobalError("Por favor ingrese el numero de tarjeta profesional del Farmaceutico a buscar");
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
			Farmaceutico farmaceutico = usuarioEJB.buscarFarmaceuticoByTarjeta(tarjetaProfesional);
			if(farmaceutico != null){
				farmaceutico.setNombre(nombre);
				farmaceutico.setApellido(apellido);
				farmaceutico.setCorreo(correo);
				farmaceutico.setGenero(genero);
				farmaceutico.setCiudad(ciudad);
				farmaceutico.setFechaNacimiento(fechaNacimiento);
				farmaceutico.setNumeroIdentificacion(numeroIdentificacion);
				farmaceutico.setTipoIdentificacion(tipoID);
				farmaceutico.setTelefono(telefono);
				farmaceutico.setPassword(password);
				usuarioEJB.editarFarmaceutico(farmaceutico);
				limpiar();
				Messages.addFlashGlobalInfo("El Farmaceutico "+nombre+" "+apellido+" se ha actualizado exitosamente");
			}else{
				Messages.addGlobalError("Por favor ingrese el numero de tarjeta profesional del Farmaceutico a buscar");
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
			usuarioEJB.eliminarFarmaceutico(tarjetaProfesional);
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

	public List<Farmaceutico> getFarmaceuticos() {
		return farmaceuticos;
	}

	public void setFarmaceuticos(List<Farmaceutico> farmaceuticos) {
		this.farmaceuticos = farmaceuticos;
	}
}