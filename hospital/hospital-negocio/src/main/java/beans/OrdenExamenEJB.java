package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.CitaMedica;
import entidades.Examen;
import entidades.OrdenCirugia;
import entidades.OrdenExamen;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class OrdenExamenEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Listar todos los examenes en la ordenes de los examenes
	 * @return, el listado de todos los examenes
	 */
	public List<OrdenExamen>listarOrdenExamenExamen(){
		Query q = em.createNamedQuery(OrdenExamen.listarExamen);
		List<OrdenExamen> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Metodo que me lista todas las citas medicas que hay para asignarlas una orden
	 * @return, la lista con la citas
	 */
	public List<CitaMedica> listaCitas(){
		Query q = em.createNamedQuery(CitaMedica.LISTA_CITA);
		List<CitaMedica> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Metodo que me trae  una lista con los examenes que hay
	 * @return
	 */
	public List<Examen> listaExamen(){
		Query q = em.createNamedQuery(Examen.listarExamen);
		List<Examen> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Metodo para listar las ordenes existentes TODAS LAS ORDENES
	 * @return, LA LISTA CON LAS ORDENES OBTENIDAS
	 */
	public List<OrdenExamen> listaOrdenes(){
		Query q = em.createNamedQuery(OrdenExamen.LISTAORDENESEXIS);
		List<OrdenExamen> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * lista de ordenes de cirugia
	 */
	public List<OrdenCirugia> listaOrdenCirugias(){
		Query q = em.createNamedQuery(OrdenCirugia.LISTAR);
		return q.getResultList();
	}
	
	/**
	 * MEtodo para crear una orden 
	 * @param ordenEx
	 */
	public void crear(OrdenExamen ordenEx){
		em.persist(ordenEx);
	}
	
	/**
	 * Crear orden de cirugia
	 */
	public void crearOrdenCirugia(OrdenCirugia orden){
		em.persist(orden);
	}
	/**
	 * Metodo para buscar una orden de un examen
	 * @param id
	 * @return
	 */
	public OrdenExamen buscar(int id){
		return em.find(OrdenExamen.class, id);
	}
	
	/**
	 * Buscar orden de cirugia
	 */
	public OrdenCirugia buscarOrdenCirugia(int id){
		return em.find(OrdenCirugia.class, id);
	}
	
	/**
	 * Metodo para editar un a orden de examen 
	 * @param orden
	 */
	public void editar(OrdenExamen orden){
		em.merge(orden);
	}
	
	/**
	 * Orden de cirugia editar
	 */
	public void editarOrdenCirugia(OrdenCirugia orden){
		em.merge(orden);
	}
	
	/**
	 * Metodo para eliminar una orden de un examen
	 * @param ordenEx
	 */
	public void eliminar(OrdenExamen ordenEx){
		em.remove(ordenEx);
	}
	
	/**
	 * Eliminar orden de cirugia
	 */
	public void eliminarOrdenCirugia(OrdenCirugia orden){
		em.remove(orden);
	}
	
	
	
}
