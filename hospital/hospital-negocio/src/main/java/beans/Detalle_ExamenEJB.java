/**
 * 
 */
package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.DetalleExamen;

/**
 * @author AlejandroM
 *
 */
@LocalBean
@Stateless
public class Detalle_ExamenEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo que creara un detalle de examen
	 * @param detalleExamen, el detalle del examen que sera creado
	 */
	public void crear (DetalleExamen detalleExamen){
		em.persist(detalleExamen);
	}
	
	
	/**
	 * Metodo para editar un detalle de examen
	 * @param detalleExamen
	 */
	public void editar(DetalleExamen detalleExamen){
		em.merge(detalleExamen);
	}
	
	
	public DetalleExamen buscar (int id){
		return em.find(DetalleExamen.class, id);
	}
	
	/**
	 * Metodo para eliminar un detalleExamen
	 * @param examen
	 */
	public void eliminar(DetalleExamen examen){
		em.remove(examen);
	}
	
	
	
	
}
