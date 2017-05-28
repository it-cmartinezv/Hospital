package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Hospitalizacion;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class HospitalizacionEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear una hospitalizacion
	 * @param hospitalizacion
	 */
	public void crear(Hospitalizacion hospitalizacion){
		em.persist(hospitalizacion);
	}
	
	/**
	 * Metodo para editar una hospitalizacion
	 * @param hospitalizacion
	 */
	public void editar(Hospitalizacion hospitalizacion){
		em.merge(hospitalizacion);
	}
	
	
	

}
