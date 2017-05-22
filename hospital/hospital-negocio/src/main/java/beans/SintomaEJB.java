package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Sintoma;

import javax.persistence.EntityManager;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class SintomaEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo que lista todos los sintomas
	 * @return, los sintomas
	 */
	public List<Sintoma> listarSintoma(){
		Query q = em.createNamedQuery(Sintoma.listarSintomas);
		List<Sintoma> lista = q.getResultList();
		return lista;
		
		
	}
	
}
