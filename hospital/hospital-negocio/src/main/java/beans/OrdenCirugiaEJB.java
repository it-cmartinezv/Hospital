package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.OrdenCirugia;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class OrdenCirugiaEJB {
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * Listar todos las cirugias en la ordenes de las cirugias
	 * @return, el listado de todas las cirugias
	 */
	public List<OrdenCirugia>listarOrdenCirugiaCirugia(){
		Query q = em.createNamedQuery(OrdenCirugia.listarCirugia);
		List<OrdenCirugia> lista = q.getResultList();
		return lista;
	}
	
}
