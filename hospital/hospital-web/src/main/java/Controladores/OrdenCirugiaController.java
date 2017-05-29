package Controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import beans.CirugiaEJB;
import beans.EJBUsuario;
import beans.OrdenExamenEJB;
import entidades.Cirugia;
import entidades.CitaMedica;
import entidades.Examen;
import entidades.Medico;
import entidades.OrdenCirugia;
import entidades.OrdenExamen;

/**
 * @author Carlos martinez
 *
 */
@Named("ordenCirugiaController")
@ViewScoped
public class OrdenCirugiaController implements Serializable{

	
	@EJB
	OrdenExamenEJB ordenEJB;
	
	@EJB
	EJBUsuario usuarioEJB;
	
	@EJB
	private CirugiaEJB cirugiaEJB;
	
	private int id;
	
	private CitaMedica cita;
	private Cirugia cirugia;
	private Date fecha;
	private String descripcion;
	private Medico medico;
	
	private List<CitaMedica> listarCitas;
	private List<Cirugia> cirugias;
	
	private List<OrdenCirugia> listaOrden;
	private List<Medico> listaMedicos;
	
	
	@PostConstruct
	public void iniciar(){
		listarCitas = ordenEJB.listaCitas();
		listaOrden = ordenEJB.listaOrdenCirugias();
		cirugias = cirugiaEJB.listarCirugia();
		listaMedicos = usuarioEJB.listarMedicos();
	}
	
	
	
	/**
	 * Metodo para crear una orden de un examen
	 */
	public void crear(){
		OrdenCirugia orden = new OrdenCirugia(cita, cirugia, medico, fecha, descripcion);
		ordenEJB.crearOrdenCirugia(orden);
		Messages.addFlashGlobalInfo("Se ha creado la orden de cirugia exitosamente");
		listaOrden = ordenEJB.listaOrdenCirugias();
		limpiar();
	}
	
	/**
	 * Metodo para eliminar una orden de examen
	 */
	public void eliminar(OrdenCirugia orden){
		ordenEJB.eliminarOrdenCirugia(orden);
		limpiar();
		listaOrden = ordenEJB.listaOrdenCirugias();
		Messages.addFlashGlobalInfo("Se ha eliminado la orden de cirugia exitosamente");
	}
	
	public void limpiar(){
		descripcion = "";
	}
	
	/**
	 * Metodo para editar una orden de examen
	 */
	public void editar(){
		OrdenCirugia or = ordenEJB.buscarOrdenCirugia(id);
		if (or!=null) {
			or.setCitaMedica(cita);
			or.setDescripcion(descripcion);
			or.setCirugia(cirugia);
			or.setFecha(fecha);
			or.setMedico(medico);
			ordenEJB.editarOrdenCirugia(or);
			listaOrden = ordenEJB.listaOrdenCirugias();
			Messages.addFlashGlobalInfo("La orden del examen se ha editado exitosamente");
			limpiar();
		} else {
			Messages.addFlashGlobalInfo("No existe ningun orden con este numero");
		}

	}
	
	
	/**
	 * Metodo para buscar las ordenes de los medicamentos
	 */
	public void buscar() {
		OrdenCirugia or = ordenEJB.buscarOrdenCirugia(id);
		if (or!=null) {
			cita = or.getCitaMedica();
			medico = or.getMedico();
			cirugia = or.getCirugia();
			fecha = or.getFecha();
			descripcion = or.getDescripcion();
		} else {
			limpiar();
			Messages.addFlashGlobalInfo("No hay ninguna orden de cirugia con este numero");
		}
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CitaMedica getCita() {
		return cita;
	}
	public void setCita(CitaMedica cita) {
		this.cita = cita;
	}

	public List<CitaMedica> getListarCitas() {
		return listarCitas;
	}
	public void setListarCitas(List<CitaMedica> listarCitas) {
		this.listarCitas = listarCitas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public List<Medico> getListaMedicos() {
		return listaMedicos;
	}

	public void setListaMedicos(List<Medico> listaMedicos) {
		this.listaMedicos = listaMedicos;
	}



	public Cirugia getCirugia() {
		return cirugia;
	}



	public void setCirugia(Cirugia cirugia) {
		this.cirugia = cirugia;
	}



	public List<Cirugia> getCirugias() {
		return cirugias;
	}



	public void setCirugias(List<Cirugia> cirugias) {
		this.cirugias = cirugias;
	}



	public List<OrdenCirugia> getListaOrden() {
		return listaOrden;
	}



	public void setListaOrden(List<OrdenCirugia> listaOrden) {
		this.listaOrden = listaOrden;
	}
	
}

