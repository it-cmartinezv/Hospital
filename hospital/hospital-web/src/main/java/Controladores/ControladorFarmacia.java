package Controladores;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import beans.FarmaciaEJB;
import entidades.Ciudad;
import entidades.Farmaceutico;
import entidades.Farmacia;
import remote.IFarmaciaRemote;

@ViewScoped
@Named("Farmacia")
@Remote(IFarmaciaRemote.class)
public class ControladorFarmacia implements Serializable{

	
	@EJB
	FarmaciaEJB farmaciaEJB;
	
	private int id;
	
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	private String nombre;
	
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	private String direccion;
	
	@Pattern(regexp="[0-9]*",message="Solo numeros")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	private String telefono;
	
	private Ciudad ciudad;
	private Farmaceutico farmaceutico;
	private String purbeaCiudad;
	private List<Ciudad>listaCiudad;
	
	private List<Farmaceutico>listaFarmaceutico;
	private List<Farmacia> farmacias;
	
	
	@PostConstruct
	public void iniciar(){
		listaCiudad = farmaciaEJB.listaCiudades();
		listaFarmaceutico = farmaciaEJB.listarFarmaceuticos();
		farmacias = farmaciaEJB.listar();
	}
	
	
	/**ahi 
	 * Metodo para crear una farmacia
	 */
	public void crearFarmacia(){
		try{
			Farmacia farma = new Farmacia(nombre, direccion, telefono, ciudad, farmaceutico);
			farmaciaEJB.crear(farma);
			Messages.addFlashGlobalInfo("Se ha registrado exitosamente la farmacia");
		}catch(Exception e){
			e.printStackTrace(); // Esto siempre hay que ponerlo para que imprima las excepciones
		}
	}
	
	/*+
	 * Metodo para buscar una farmacia
	 */
	public void buscar(){
		Farmacia far = farmaciaEJB.buscarNombre(nombre);
		if (far!=null) {
			direccion = far.getDireccion();
			telefono = far.getTelefono();
			ciudad = far.getCiudad();
			farmaceutico = far.getFarmaceutico();				
		}else {
			Messages.addFlashGlobalInfo("No hay ninguna farmacia con ese dato");
		}
		
	}
	
	/**
	 * Metodo para editar una farmacia
	 */
	public void editar(){
		Farmacia far = farmaciaEJB.buscarNombre(nombre);
		if (far!=null) {
			far.setNombre(nombre);
			far.setTelefono(telefono);
			far.setDireccion(direccion);
			far.setCiudad(ciudad);
			far.setFarmaceutico(farmaceutico);
			farmaciaEJB.editar(far);
			Messages.addFlashGlobalInfo("Se ha editado exitosamente");
		}else {
			Messages.addFlashGlobalInfo("No hay ninguna farmacia con ese dato");
		}
	}
	
	/**
	 * Metodo para eliminar una farmacia
	 */
	public void eliminar(){
		Farmacia far = farmaciaEJB.buscarNombre(nombre);
		System.out.println(far);
		if (far!=null) {
			farmaciaEJB.eliminar(far);
			Messages.addFlashGlobalInfo("Se ha eliminado exitosamente la farmacia");
		}else {
			Messages.addFlashGlobalInfo("No hay ninguna farmacia con ese dato");
		}
	}
	
	
	
	
	public String getPurbeaCiudad() {
		return purbeaCiudad;
	}


	public void setPurbeaCiudad(String purbeaCiudad) {
		this.purbeaCiudad = purbeaCiudad;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public Farmaceutico getFarmaceutico() {
		return farmaceutico;
	}
	public void setFarmaceutico(Farmaceutico farmaceutico) {
		this.farmaceutico = farmaceutico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Ciudad> getListaCiudad() {
		return listaCiudad;
	}

	public void setListaCiudad(List<Ciudad> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}

	public List<Farmaceutico> getListaFarmaceutico() {
		return listaFarmaceutico;
	}

	public void setListaFarmaceutico(List<Farmaceutico> listaFarmaceutico) {
		this.listaFarmaceutico = listaFarmaceutico;
	}


	public List<Farmacia> getFarmacias() {
		return farmacias;
	}


	public void setFarmacias(List<Farmacia> farmacias) {
		this.farmacias = farmacias;
	}
}