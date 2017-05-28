package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Cama;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class CamaEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear una cama
	 * @param cama
	 */
	public void crear(Cama cama){
		em.persist(cama);
	}
	
	/**
	 * Metodo para buscar una cama
	 * @param id
	 * @return
	 */
	public Cama buscar(int id){
		return em.find(Cama.class, id);
	}
	
	/**
	 * Metodo para editar una cama
	 * @param cama
	 */
	public void editar(Cama cama){
		em.merge(cama);
	}
	
	/**
	 * Listar las camas
	 * @return, el listado de las camas
	 */
	public List<Cama> listarCamas(){
		Query q = em.createNamedQuery(Cama.listarCama);
		List<Cama> lista = q.getResultList();
		return lista;
	}
	
	

}
