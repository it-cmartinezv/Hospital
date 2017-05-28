package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Tratamiento;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class TratamientoEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**\
	 * Metodo para buscar un tratamiento
	 * @param id
	 * @return
	 */
	public Tratamiento buscar(int id){
		return em.find(Tratamiento.class,id);
	}
	
	/**
	 * Metodo que lista todos los tratamientos
	 * @return, los tratamientos
	 */
	public List<Tratamiento> listarTratamiento(){
		Query q = em.createNamedQuery(Tratamiento.listarTratamiento);
		List<Tratamiento> lista = q.getResultList();
		return lista;
	}
	
}
