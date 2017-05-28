package beans;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidades.Enfermedad;
import entidades.Sintoma;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class EnfermedadEJB {
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo para crear una enfermedad
	 * @param enfermedad
	 */
	public void crear(Enfermedad enfermedad){
		em.persist(enfermedad);
	}
	
	/**
	 * Metodo para buscar enfermedad
	 * @param enfermedad
	 */
	public void editar(Enfermedad enfermedad){
		em.merge(enfermedad);
	}
	
	
	/**
	 * Listar los sintomas de la enfermedad
	 * @return, el listado de los sintomas de una enfermedad
	 */
	public List<Enfermedad>listarEnfermedadSintoma(){
		Query q = em.createNamedQuery(Sintoma.listarSintomas);
		List<Enfermedad> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Listar los tratamientos de una enfermedad
	 * @return, el listado de tratamientos de una enfermedad
	 */
	public List<Enfermedad>listarEnfermedadTratamiento(){
		Query q = em.createNamedQuery(Enfermedad.listarEnfermedadTratamiento);
		List<Enfermedad> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Listar las enfermedades
	 * @return, el listado de las enfermedades
	 */
	public List<Enfermedad> listarEnfermedad(){
		Query q = em.createNamedQuery(Enfermedad.listarEnfemedad);
		List<Enfermedad> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Buscar enfermedad por nombre
	 * @param nombre
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Enfermedad enfermedadByNombre(String nombre){
		Query q =em.createNamedQuery(Enfermedad.BYNOMBRE);
		q.setParameter(1,nombre);
		List<Enfermedad> lista =q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
		
	}
	
	/**
	 * Metodo para eliminar una enfermedad
	 * @param nombre
	 */
	public void eliminar(String nombre){
		Enfermedad enfermedad = enfermedadByNombre(nombre);
		if(enfermedad != null){
			em.remove(enfermedad);
		}else{
			throw new ExcepcionNegocio("No se ha encontrado ninguna enfermedad con el nombre "+nombre);
		}
	}
	
	public Enfermedad buscar(int id){
		return em.find(Enfermedad.class, id);
	}
	
}
