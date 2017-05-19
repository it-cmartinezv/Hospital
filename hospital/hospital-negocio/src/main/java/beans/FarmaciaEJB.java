package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Ciudad;
import entidades.Farmaceutico;
import entidades.Farmacia;
import entidades.TipoMedicamento;

/**
 * @author AlejandroM
 *
 */
@LocalBean
@Stateless
public class FarmaciaEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear una farmacia
	 * @param farmacia
	 */
	public void crear(Farmacia farmacia){
	 em.persist(farmacia);	
	}
	
	/**
	 * Metodo para buscar una farmacia
	 * @param id, el id de la farmacia a buscar
	 * @returnla farmacia si se encuentra
	 */
	public Farmacia buscar(int id){
		return em.find(Farmacia.class, id);
	}
	
	/**
	 * Metodo para editar una farmacia
	 * @param farmacia
	 */
	public void editar(Farmacia farmacia){
		em.merge(farmacia);
	}
	
	/**
	 * Metodo para eliminar una farmacia
	 * @param farmacia
	 */
	public void eliminar(Farmacia farmacia) {
		em.remove(farmacia);

	}
	
	/**
	 * Lista que me trae todas las ciudades 
	 * @return, la lista con las ciudades
	 */
	public List<Ciudad>listaCiudades(){
		Query q = em.createNamedQuery(Ciudad.LISTAR);
		List<Ciudad> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Metodo que me trae todas las farmacias que hay 
	 * @return, una lista con todos los farmaceuticos que estan registrados en el sistema
	 */
	public List<Farmaceutico> listarFarmaceuticos(){
		Query q = em.createNamedQuery(Farmaceutico.listarFarmaceuticos);
		List<Farmaceutico> lista = q.getResultList();
		return lista;
	}
	

	
	
}
