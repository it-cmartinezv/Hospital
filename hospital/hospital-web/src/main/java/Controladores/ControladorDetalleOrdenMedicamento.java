/**
 * 
 */
package Controladores;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.DetalleOrdenMediEJB;
import entidades.Medicamento;
import entidades.OrdenMedicamento;
import entidades.detalleOrdenMedicamente;

/**
 * @author AlejandroM
 *
 */
@ViewScoped
@Named("detalleOrden")
public class ControladorDetalleOrdenMedicamento implements Serializable {

	@EJB
	private DetalleOrdenMediEJB ordenMediEJB;

	private int id;
	private OrdenMedicamento ordenMedicamento;

	private Medicamento medicamento;

	private int cantidad;

	private boolean estado;

	private List<Medicamento> listaMEdicamenos;

	private List<OrdenMedicamento> listaOrdenes;

	private List<detalleOrdenMedicamente> listaDetalle;

	/**
	 * PostrConstruc
	 */
	@PostConstruct
	public void iniciar() {
		listaMEdicamenos = ordenMediEJB.listaMedicamento();
		listaOrdenes = ordenMediEJB.listarOrden();
		listaDetalle = ordenMediEJB.listaDetalle();
		listaDetalle = ordenMediEJB.listaEntre();
	}

	/**
	 * Metodo para crear un detalle de medicamento
	 */
	public void crearDetalle() {

		// El estado del detalle es false porque cuando recien se crea sale no
		// entrega y el farmaceutico se encarga de cambiarlo a true
		detalleOrdenMedicamente detalle = new detalleOrdenMedicamente(ordenMedicamento, medicamento, cantidad, false);
		System.out.println(medicamento);

		System.out.println(ordenMedicamento);
		ordenMediEJB.crearDetalleOrden(detalle);
		Messages.addFlashGlobalInfo("La orden se ha creado exitosamente");
	}

	/**
	 * Metodo para buscar los medicamentos de una orden especifica
	 * 
	 * @param id,
	 *            el id de la orden a buscar
	 */
	public void listarDetallesORden() {
		listaDetalle = ordenMediEJB.listaMedicamentos(ordenMedicamento);
	}

	/**
	 * Metodo que me va a buscar una ordenDeMedicamento por su id 
	 * si encuentra la orden va a listarme los medicamentos que corresponden a esa orden
	 * de lo contrario dara un mensaje de error
	 */
	public void buscarOrdenMEdicamento() {
		ordenMedicamento = ordenMediEJB.buscarOrdenMedi(id);
		if (ordenMedicamento != null) {
			listarDetallesORden();

		} else {
			Messages.addFlashGlobalInfo("No se ha encontrado ninguna orden de medicamento con el id " + id);	
		}
	}
	
	/**
	 * 
	 * @param detalleOrdenMedicamente
	 */
	public void entregarMedicamento(detalleOrdenMedicamente detalleOrdenMedicamente){
		detalleOrdenMedicamente.setEntregado(true);
		ordenMediEJB.editar(detalleOrdenMedicamente);
		listaDetalle=ordenMediEJB.listaMedicamentos(detalleOrdenMedicamente.getOrdenMedicamento());
		if (listaDetalle== null) {
			detalleOrdenMedicamente.getOrdenMedicamento().setEstado(true);
			ordenMediEJB.editarOrden(detalleOrdenMedicamente.getOrdenMedicamento());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public List<detalleOrdenMedicamente> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<detalleOrdenMedicamente> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

	public DetalleOrdenMediEJB getOrdenMediEJB() {
		return ordenMediEJB;
	}

	public void setOrdenMediEJB(DetalleOrdenMediEJB ordenMediEJB) {
		this.ordenMediEJB = ordenMediEJB;
	}

	public OrdenMedicamento getOrdenMedicamento() {
		return ordenMedicamento;
	}

	public void setOrdenMedicamento(OrdenMedicamento ordenMedicamento) {
		this.ordenMedicamento = ordenMedicamento;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Medicamento> getListaMEdicamenos() {
		return listaMEdicamenos;
	}

	public void setListaMEdicamenos(List<Medicamento> listaMEdicamenos) {
		this.listaMEdicamenos = listaMEdicamenos;
	}

	public List<OrdenMedicamento> getListaOrdenes() {
		return listaOrdenes;
	}

	public void setListaOrdenes(List<OrdenMedicamento> listaOrdenes) {
		this.listaOrdenes = listaOrdenes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
