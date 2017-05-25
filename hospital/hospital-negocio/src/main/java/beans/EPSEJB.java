/**
 * 
 */
package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
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
import remote.IEpsRemote;
import remote.ILocalizacionRemote;

/**
 * @author AlejandroM
 *
 */
@LocalBean
@Stateless
@Remote(IEpsRemote.class)
public class EPSEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo para crear una eps
	 * 
	 * @param eps,
	 *            la eps que sera creada
	 */
	public void crear(Eps eps) {
		em.persist(eps);

	}

	/**
	 * Metodo para buscar una eps por su id
	 * 
	 * @param id,
	 *            el id de la farmacia a buscar @return, la eps si se encontro
	 *            de lo contrario sale null
	 */
	public Eps buscar(int id) {
		return em.find(Eps.class, id);
	}

	public TipoEps buscarTipo(int id) {
		return em.find(TipoEps.class, id);
	}

	/**
	 * Metodo que busca una EPS por su nombre
	 * 
	 * @param nombre,
	 *            el nombre que va a buscar @return, una lista con las EPS que
	 *            tengan ese nombre
	 */
	public Eps buscarPorNombre(String nombre) {
		Query q = em.createNamedQuery(Eps.LISTAR_NOMBRE);
		q.setParameter(1, nombre);
		List<Eps> lista = q.getResultList();
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	/**
	 * Metodo para editar una eps
	 * 
	 * @param nombre, el nombre por el cual se validara que la eps si exista
	 */
	public void editar(Eps eps) {
		em.merge(eps);
	}

	/**
	 * Metodo para eliminar una eps
	 * 
	 * @param eps,
	 *            la eps que se va a eliminar
	 */
	public void eliminar(Eps eps) {
		Eps LaEps = buscarPorNombre(eps.getNombre());
		if (LaEps != null) {
			em.remove(eps);
		} else {
			throw new excepciones.ExcepcionNegocio("No se ha encontrado ninguna eps para eliminar");
		}

	}

	/**
	 * Lista que me trae los diferentes tipos de eps @return, una lista con los
	 * tipos de eps que hay
	 */
	public List<TipoEps> listaTipos() {
		Query q = em.createNamedQuery(TipoEps.listaTipos);
		List<TipoEps> lista = q.getResultList();
		return lista;
	}

	/**
	 * Listar las eps registradas en la base de datos
	 * 
	 * @return toda la lista de eps
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Eps> listar() {
		return em.createNamedQuery(Eps.LISTAR).getResultList();
	}

}
