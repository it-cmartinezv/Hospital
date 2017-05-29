package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cirugia;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class CirugiaEJB {

	@PersistenceContext
	private EntityManager em;
	
	public Cirugia buscar(int id){
		return em.find(Cirugia.class, id);
	}
	/**
	 * Metodo para crear una cirugia
	 * @param cirugia
	 */
	public void crear(Cirugia cirugia){
		em.persist(cirugia);
	}
	
	/**
	 * Metodo para editar una cirugia
	 * @param cirugia
	 */
	public void editar(Cirugia cirugia){
		em.merge(cirugia);
	}
	
	/**
	 * Metodo para eliminar una cirugia
	 * @param cirugia
	 */
	public void eliminar(String nombre){
		Cirugia cirugia = cirugiaByNombre(nombre);
		if(cirugia != null){
			em.remove(cirugia);
		}else{
			throw new ExcepcionNegocio("No se ha encontrado ninguna cirugia con el nombre "+nombre);
		}
	}
	
	/**
	 * Buscar cirugia por nombre
	 * @param nombre
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cirugia cirugiaByNombre(String nombre){
		Query q = em.createNamedQuery(Cirugia.BYNOMBRE);
		q.setParameter(1,nombre);
		List<Cirugia> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Listar los quirofanos donde se realiza la cirugia
	 * @return, el listado de los quirofanos para realizar la cirugia
	 */
	public List<Cirugia>listarCirugiaQuirofano(){
		Query q = em.createNamedQuery(Cirugia.listarCirugiaQuirofano);
		List<Cirugia> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Listar las cirugias
	 * @return, el listado de las cirugias
	 */
	public List<Cirugia>listarCirugia(){
		Query q = em.createNamedQuery(Cirugia.listarCirugia);
		List<Cirugia> lista = q.getResultList();
		return lista;
	}
}
