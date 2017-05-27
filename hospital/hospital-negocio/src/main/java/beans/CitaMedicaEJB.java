package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.CitaMedica;
import entidades.Sintoma;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class CitaMedicaEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo que lista todos los sintomas al momento de la cita
	 * @return, lista de todos los sintomas
	 */
	public List<Sintoma> listarSintomas(){
		Query q = em.createNamedQuery(Sintoma.listarSintomas);
		List<Sintoma> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * MEtodo para buscar una cita por su ID este metodo se utiliza principalmente en el converter
	 * @param id
	 * @return
	 */
	public CitaMedica buscar(int id){
		return em.find(CitaMedica.class, id);
	}
	
}
