/**
 * 
 */
package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Ciudad;
import entidades.Eps;
import entidades.Pais;
import entidades.TipoEps;

/**
 * @author AlejandroM
 *
 */
@LocalBean
@Stateless
public class EPSEJB {

	

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear una eps
	 * @param eps, la eps que sera creada
	 */
	public void crear (Eps eps){
		em.persist(eps);
		
	}
	
	/**
	 * Metodo para buscar una eps por su id
	 * @param id, el id de la farmacia a buscar
	 * @return, la eps si se encontro de lo contrario sale null
	 */
	public Eps buscar(int id){
		return em.find(Eps.class, id);
	}
	
	/**
	 * Metodo para editar una farmacia
	 * @param eps, la farmacia que se va a editar
	 */
	public void editar(Eps eps){
		em.merge(eps);
	}
	
	/**
	 * Metodo para eliminar una eps
	 * @param eps, la eps que se va a eliminar
	 */
	public void eliminar(Eps eps){
		em.remove(eps);
	}
	
	/**
	 * Lista que me trae los diferentes tipos de eps
	 * @return, una lista con los tipos de eps que hay
	 */
	public List<TipoEps> listaTipos(){
		Query q = em.createNamedQuery(TipoEps.listaTipos);
		List<TipoEps> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Listar las eps registradas en la base de datos
	 * @return toda la lista de eps
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Eps> listar(){
		return em.createNamedQuery(Eps.LISTAR).getResultList();
	}
	
}
