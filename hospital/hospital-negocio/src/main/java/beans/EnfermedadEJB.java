package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Enfermedad;
import entidades.Sintoma;

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
	 * Listar los sintomas de la enfermedad
	 * @return, el listado de los sintomas de una enfermedad
	 */
//	public List<Enfermedad>listarEnfermedadSintoma(){
//		Query q = em.createNamedQuery(Enfermedad.listarEnfermedadSintoma);
//		List<Enfermedad> lista = q.getResultList();
//		return lista;
//	}
	
	/**
	 * Listar los tratamientos de una enfermedad
	 * @return, el listado de tratamientos de una enfermedad
	 */
	public List<Enfermedad>listarEnfermedadTratamiento(){
		Query q = em.createNamedQuery(Enfermedad.listarEnfermedadTratamiento);
		List<Enfermedad> lista = q.getResultList();
		return lista;
	}
}
