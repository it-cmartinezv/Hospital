package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.OrdenExamen;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class OrdenExamenEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Listar todos los examenes en la ordenes de los examenes
	 * @return, el listado de todos los examenes
	 */
	public List<OrdenExamen>listarOrdenExamenExamen(){
		Query q = em.createNamedQuery(OrdenExamen.listarExamen);
		List<OrdenExamen> lista = q.getResultList();
		return lista;
	}
	
}
