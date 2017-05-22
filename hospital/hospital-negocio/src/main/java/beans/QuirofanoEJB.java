package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Quirofano;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class QuirofanoEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para buscar un quirofano
	 * @param id
	 * @return
	 */
	public Quirofano buscar(int id){
		return em.find(Quirofano.class, id);
	}
	
	/**
	 * Metodo que lista todos los quirofanos
	 * @return, los quirofanos
	 */
	public List<Quirofano> listarQuirofano(){
		Query q = em.createNamedQuery(Quirofano.listarQuirofano);
		List<Quirofano> lista = q.getResultList();
		return lista;
	}
	
}
