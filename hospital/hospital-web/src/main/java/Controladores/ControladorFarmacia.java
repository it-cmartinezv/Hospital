package Controladores;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import beans.FarmaciaEJB;
import entidades.Ciudad;
import entidades.Farmaceutico;
import entidades.Farmacia;

@ViewScoped
@Named("Farmacia")
public class ControladorFarmacia implements Serializable{

	
	@EJB
	FarmaciaEJB farmaciaEJB;
	
	private int id;
	private String nombre;
	private String direccion;
	private String telefono;
	private Ciudad ciudad;
	private Farmaceutico farmaceutico;
	private String purbeaCiudad;
	private List<Ciudad>listaCiudad;
	
	private List<Farmaceutico>listaFarmaceutico;
	
	@PostConstruct
	public void iniciar(){
		listaCiudad = farmaciaEJB.listaCiudades();
		//listaFarmaceutico = farmaciaEJB.listarFarmaceuticos();
	}
	
	
	/**
	 * Metodo para crear una farmacia
	 */
	public void crearFarmacia(){
		Farmacia farma = new Farmacia(id, nombre, direccion, telefono, ciudad, farmaceutico);
		farmaciaEJB.crear(farma);
		Messages.addFlashGlobalInfo("Se ha creado exitosamente");
		
	}
	
	/*+
	 * Metodo para buscar una farmacia
	 */
	public void buscar(){
		Farmacia far = farmaciaEJB.buscar(id);
		if (far!=null) {
			nombre = far.getNombre();
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
		Farmacia far = farmaciaEJB.buscar(id);
		if (far!=null) {
			
			far.setNombre(nombre);
			far.setTelefono(telefono);
			far.setDireccion(direccion);
			far.setCiudad(ciudad);
			far.setFarmaceutico(farmaceutico);
			farmaciaEJB.editar(far);
			Messages.addFlashGlobalInfo("Se ha eliminado exitosamente");
		}else {
			Messages.addFlashGlobalInfo("No hay ninguna farmacia con ese dato");
		}
	}
	
	/**
	 * Metodo para eliminar una farmacia
	 */
	public void eliminar(){
		Farmacia far = farmaciaEJB.buscar(id);
		if (far!=null) {
			farmaciaEJB.eliminar(far);
			Messages.addFlashGlobalInfo("Se ha eliminado exitosamente");
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
	
	
	
	
}
