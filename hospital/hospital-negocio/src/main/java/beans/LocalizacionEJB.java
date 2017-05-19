package beans;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Ciudad;
import entidades.Departamento;
import entidades.Pais;
import remote.ILocalizacionRemote;

/**
 * Clase encargada de gestionar la localizacion
 * listar paises,departamentos,ciudades
 */

@LocalBean
@Stateless 
@Remote(ILocalizacionRemote.class)
public class LocalizacionEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Listar paises
	 * @return toda la lista de paises
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Pais> listarPaises(){
		return em.createNamedQuery(Pais.LISTAR).getResultList();
	}
	
	/**
	 * Listar departamentos
	 * @return toda la lista de departamentos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Departamento> listarDepartamentos(){
		return em.createNamedQuery(Departamento.LISTAR).getResultList();
	}
	
	/**
	 * Listar Ciudades
	 * @return toda la lista de ciudades
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Ciudad> listarCiudades(){
		return em.createNamedQuery(Ciudad.LISTAR).getResultList();
	}
}
