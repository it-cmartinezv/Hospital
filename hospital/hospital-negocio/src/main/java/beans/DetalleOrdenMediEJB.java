/**
 * 
 */
package beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Medicamento;
import entidades.OrdenMedicamento;
import entidades.detalleOrdenMedicamente;

/**
 * @author AlejandroM
 *
 */
@LocalBean
@Stateless
public class DetalleOrdenMediEJB implements Serializable{

	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear una detalle d eorden de medicamento
	 * @param ordenMedicamente
	 */
	public void crearDetalleOrden(detalleOrdenMedicamente ordenMedicamente){
		em.persist(ordenMedicamente);
	}
	
	
	/**
	 * Lista que me trae todos los medicamentos que estan en la BD
	 * @return
	 */
	public List<Medicamento> listaMedicamento(){
		Query q = em.createNamedQuery(Medicamento.LISTARMEDICAMENTOS);
		List<Medicamento> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Lista que me trae todas las ordenes medicas a las cuales se les va a entregar una cantidad
	 * determinada de cada medicamento
	 * @return
	 */
	public List<OrdenMedicamento> listarOrden(){
		Query q = em.createNamedQuery(OrdenMedicamento.LISTARORDENES);
		List<OrdenMedicamento> lista = q.getResultList();
		return lista;
	}
	
	
	/**
	 * Esta lista me va a traer los detalles de los medicamentos 
	 * esto sera para ver en una tabla la cantidad de medicamentos pedidos la cita y el medicamento
	 * @return
	 */
	public List<detalleOrdenMedicamente> listaDetalle(){
		Query q = em.createNamedQuery(detalleOrdenMedicamente.LISTA_DETALLES);
		List<detalleOrdenMedicamente> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Esta lista corresponde a los medicamentos que tiene una orden en especial
	 * @return, la lista con los medicamento de una orden especifica 
	 */
	public List<detalleOrdenMedicamente> listaMedicamentos(OrdenMedicamento ordenMedicamento){
		Query q = em.createNamedQuery(detalleOrdenMedicamente.LISTA_ORDEN_MEDICA);
		q.setParameter(1, ordenMedicamento);
		return q.getResultList();

	}
	
	/**
	 * Buscar una orden del medicamento por su id
	 * @param id, el id de la orden del medicamento que se va a buscar
	 * @return, la orden del medicamento si se encuentra
	 */
	public OrdenMedicamento buscarOrdenMedi(int id){
		return em.find(OrdenMedicamento.class, id);
	}
	
	
	
	/**
	 * Metodo para editar el detalle del medicamento 
	 * @param detalle
	 */
	public void editar(detalleOrdenMedicamente detalle){
		em.merge(detalle);
	}
	
	
	
	public void editarOrden(OrdenMedicamento orden){
		em.merge(orden);
	}
	
	
	/**
	 * Esta lista trae todos los detalles de medicamentos entregados 
	 * @return
	 */
	public List<detalleOrdenMedicamente> listaEntre(){
		Query q = em.createNamedQuery(detalleOrdenMedicamente.LISTA_ORDEN_ENTREGADAS);
		List<detalleOrdenMedicamente> lista = q.getResultList();
		return lista;
	}
}
