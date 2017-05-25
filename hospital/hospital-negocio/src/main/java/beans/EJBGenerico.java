package beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import util.DAOGenerico;

/**
 * EJB Generico encargado de las operaciones de persistencia
 * @author carlos alfredo martinez, clase realizada por Ing Camilo Ferrer
 */
public abstract class EJBGenerico<T> {

	/**
	 * Entitymanager.
	 */
	@PersistenceContext
	protected transient EntityManager em;

	/**
	 * usuario en sesion.
	 */
	private String usuario;

	/**
	 * clase que administra este bean.
	 */
	private Class<T> clase;

	/**
	 * dao por medio del cual se hacen las operaciones a la BD a traves del
	 * Entitymanager.
	 */
	protected DAOGenerico dao;

	/**
	 * Metodo para inicializar el Bean.
	 * 
	 * @author Camilo Andres Ferrer Bustos.
	 */
	@PostConstruct
	public void initBean() {
		dao = new DAOGenerico(em);
		init();
	}

	/**
	 * Metodo para ser sobreescrito por las clases de las que hereden esta para
	 * la inicializacion de sus parametros.
	 */
	public abstract void init();

	public void crear(T entidad)  {
		dao.persistir(entidad);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public T buscar(Object pk) {
		return dao.encontrarPorId(clase, pk);
	}

	public void editar(T entidad)  {
		dao.actualizar(entidad);

	}

	public void eliminar(Object pk) {
		dao.eliminar(clase, pk);

	}

	@SuppressWarnings("unchecked")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> listar() {
		Query q = em.createQuery("SELECT o FROM " + clase.getSimpleName() + " o");
		return q.getResultList();
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
		dao = new DAOGenerico(em);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
		if (dao != null) {
			dao.setUsuario(usuario);
		} else {
			dao = new DAOGenerico(em);
			dao.setUsuario(usuario);

		}
	}

	public void setClase(Class<T> clase) {
		this.clase = clase;
	}

}
