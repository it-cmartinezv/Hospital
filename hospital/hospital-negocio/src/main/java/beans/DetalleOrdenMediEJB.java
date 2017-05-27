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
	
	
}
