package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Hospitalizacion;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class HospitalizacionEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear una hospitalizacion
	 * @param hospitalizacion
	 */
	public void crear(Hospitalizacion hospitalizacion){
		em.persist(hospitalizacion);
	}
	
	/**
	 * Metodo para editar una hospitalizacion
	 * @param hospitalizacion
	 */
	public void editar(Hospitalizacion hospitalizacion){
		em.merge(hospitalizacion);
	}
	
	/**
	 * Metodo para buscar una hospitalizacion
	 * @param id
	 * @return
	 */
	public Hospitalizacion buscar(int id){
		return em.find(Hospitalizacion.class, id);
	}
	
	/**
	 * Listar las hospitalizaciones
	 * @return, el listado de las hospitalizaciones
	 */
	public List<Hospitalizacion> listarHospitalizaciones(){
		Query q = em.createNamedQuery(Hospitalizacion.listarHospitalizacion);
		List<Hospitalizacion> lista = q.getResultList();
		return lista;
	}
	

}
