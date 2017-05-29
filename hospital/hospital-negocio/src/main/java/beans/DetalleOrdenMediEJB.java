/**
 * 
 */
package beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Medicamento;
import entidades.OrdenMedicamento;
import entidades.detalleOrdenMedicamente;
import entidades.detalleOrdenMedicamentePK;
import excepciones.ExcepcionNegocio;

/**
 * @author AlejandroM
 *
 */
@LocalBean
@Stateless
public class DetalleOrdenMediEJB implements Serializable{

	
	@PersistenceContext
	private EntityManager em;
	
	
	@EJB
	OrdenMedicamentoEJB ordenMedicamentoEJB;
	
	@EJB
	MedicamentoEJB medicamentoEJB;
	
	
	/**
	 * Metodo para crear una detalle d eorden de medicamento
	 * @param ordenMedicamente
	 */
	public void crearDetalleOrden(detalleOrdenMedicamente ordenMedicamente){
		OrdenMedicamento orden = ordenMedicamentoEJB.buscar(ordenMedicamente.getOrdenMedicamento().getId());
		if (orden!=null) {
			Medicamento medi = medicamentoEJB.buscar(ordenMedicamente.getMedicamento().getId());
			if (medi!=null) {
				detalleOrdenMedicamente detalle = buscar(ordenMedicamente.getMedicamento(), ordenMedicamente.getOrdenMedicamento());
				
				if (detalle==null) {
					ordenMedicamente.setMedicamento(medi);
					ordenMedicamente.setOrdenMedicamento(orden);
					em.persist(ordenMedicamente);
				} else {
					throw new ExcepcionNegocio("Ya registraste esta orden ");
				}
			}else {
				throw new ExcepcionNegocio("Medicamento no existe");
			}
		}else {
			throw new ExcepcionNegocio("Orden no existe");
		}
		
	}
	
	
	
	
	public void editarDetalleOrden(detalleOrdenMedicamente ordenMedicamente){
		OrdenMedicamento orden = ordenMedicamentoEJB.buscar(ordenMedicamente.getOrdenMedicamento().getId());
		if (orden!=null) {
			Medicamento medi = medicamentoEJB.buscar(ordenMedicamente.getMedicamento().getId());
			if (medi!=null) {
				detalleOrdenMedicamente detalle = buscar(ordenMedicamente.getMedicamento(), ordenMedicamente.getOrdenMedicamento());
				
				if (detalle!=null) {
					ordenMedicamente.setMedicamento(medi);
					ordenMedicamente.setOrdenMedicamento(orden);
					em.merge(ordenMedicamente);
				} else {
					throw new ExcepcionNegocio("No has registrado esta orden ");
				}
			}else {
				throw new ExcepcionNegocio("Medicamento no existe");
			}
		}else {
			throw new ExcepcionNegocio("Orden no existe");
		}
		
	}
	
	
	/**
	 * Metodo para buscar una orden de un medicamento, busca las dos foraneas
	 * @param medicamento
	 * @param ordenMedicamento
	 * @return
	 */
	public detalleOrdenMedicamente buscar(Medicamento medicamento, OrdenMedicamento ordenMedicamento){
	
		detalleOrdenMedicamentePK pk = new detalleOrdenMedicamentePK();
		pk.setMedicamento(medicamento.getId());
		pk.setOrdenMedicamento(ordenMedicamento.getId());
		return em.find(detalleOrdenMedicamente.class, pk);
		
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
		Query q = em.createNamedQuery(detalleOrdenMedicamente.LISTA_DETALLES);
		List<detalleOrdenMedicamente> lista = q.getResultList();
		return lista;
	}
}
