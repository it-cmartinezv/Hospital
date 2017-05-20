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
import entidades.Departamento;
import entidades.Pais;
import remote.ILocalizacionRemote;

/**
 * Clase encargada de gestionar la localizacion
 * listar paises,departamentos,ciudades
 * listar departamentos por pais
 * listar ciudades por departamento
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
	 * Buscar Departamento
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Pais buscarPais(int id){
		Pais pais = em.find(Pais.class, id);
		return pais;
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
	 * Lista de departamentos de un respectivo pais
	 */
	public List<Departamento> departamentosByPais(Pais pais){
		Query q = em.createNamedQuery(Departamento.BYPAIS);
		q.setParameter(1, pais);
		return q.getResultList();
	}
	
	/**
	 * Buscar Departamento
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Departamento buscarDepartamento(int id){
		Departamento departamento = em.find(Departamento.class, id);
		return departamento;
	}
	
	/**
	 * Listar Ciudades
	 * @return toda la lista de ciudades
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Ciudad> listarCiudades(){
		return em.createNamedQuery(Ciudad.LISTAR).getResultList();
	}
	
	/**
	 * Lista de ciudades de un respectivo Departamento
	 */
	public List<Ciudad> ciudadesByDepartamento(Departamento departamento){
		Query q = em.createNamedQuery(Ciudad.BYDEPARTAMENTO);
		q.setParameter(1, departamento);
		return q.getResultList();
	}
	
	/**
	 * Buscar ciudad
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Ciudad buscarCiudad(int id){
		Ciudad ciudad = em.find(Ciudad.class, id);
		return ciudad;
	}
	
	
}
