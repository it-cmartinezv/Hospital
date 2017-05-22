package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Examen;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class ExamenEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear un examen
	 * @param examen
	 */
	public void crear(Examen examen){
		em.persist(examen);
	}
	
	/**
	 * Metodo para editar un examen
	 * @param examen
	 */
	public void editar(Examen examen){
		em.merge(examen);
	}
	
	
	
}
