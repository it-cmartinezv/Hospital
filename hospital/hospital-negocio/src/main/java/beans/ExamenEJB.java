package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Examen;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class ExamenEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear un examen
	 * @param examen
	 */
	public void crear(Examen examen){
		em.persist(examen);
	}
	
	/**
	 * Metodo para editar un examen
	 * @param examen
	 */
	public void editar(Examen examen){
		em.merge(examen);
	}
	
	/**
	 * Buscar examen por nombre
	 * @param nombre
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Examen examenByNombre(String nombre){
		Query q = em.createNamedQuery(Examen.BYNOMBRE);
		q.setParameter(1, nombre);
		List<Examen> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	/**
	 * Metodo para eliminar un examen
	 * @param nombre
	 */
	public void eliminar(String nombre){
		Examen examen = examenByNombre(nombre);
		if(examen !=null){
			em.remove(examen);
		}else{
			throw new ExcepcionNegocio("No se ha encontrado ningun examen con el nombre "+nombre);
		}
	}
	
	/**
	 * Listar los examenes
	 * @return, el listado de los examenes
	 */
	public List<Examen> listarExamen(){
		Query q = em.createNamedQuery(Examen.listarExamen);
		List<Examen> lista = q.getResultList();
		return lista;
	}
	
	
	/**
	 * Metodo para buscar un examen por su id (ESTE es para el converter)
	 * @param id
	 * @return
	 */
	public Examen buscar(int id){
		return em.find(Examen.class, id);
	}
	
}
