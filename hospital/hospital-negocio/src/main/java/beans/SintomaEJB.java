package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Sintoma;

import javax.persistence.EntityManager;

/**
 * 
 * @author Sebastian Salazar
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Sintoma> listarSintoma(){
		Query q = em.createNamedQuery(Sintoma.listarSintomas);
		return q.getResultList();		
	}
	
	/**
	 * Buscar
	 */
	public Sintoma buscar(int id){
		return em.find(Sintoma.class, id);
	}
}
