package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.CitaMedica;
import entidades.Hospitalizacion;
import entidades.OrdenHospitalizacion;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class OrdenHospitalizacionEJB {
	
	@EJB
	private HospitalizacionEJB hospitalizacionEJB;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Listar todas las ordenes de las hospitalizaciones
	 * @return, el listado de todoas las hopspitalizaciones
	 */
	public List<OrdenHospitalizacion> listarOrdenHospitalizacion(){
		Query q = em.createNamedQuery(OrdenHospitalizacion.listarOrdenHospitalizacion);
		List<OrdenHospitalizacion> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Metodo que lista todas las citas medicas que hay para asignarlas a una orden hospitalizacion
	 * @return, la lista de las citas
	 */
	public List<CitaMedica> listarCitas(){
		Query q = em.createNamedQuery(CitaMedica.LISTA_CITA);
		List<CitaMedica> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Metodo que me trae una lista de las hospitalizaciones que hay
	 * @return
	 */
	public List<Hospitalizacion> listaHospitalizacion(){
		Query q = em.createNamedQuery(Hospitalizacion.listarHospitalizacion);
		List<Hospitalizacion> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * Metodo para crear una orden hospitalizacion
	 * @param ordenHospitalizacion
	 */
	public void crear(OrdenHospitalizacion ordenHospitalizacion){
		Hospitalizacion hospitalizacion = hospitalizacionEJB.buscar(ordenHospitalizacion.getId());
		if(hospitalizacion != null){
			em.persist(ordenHospitalizacion);
		}else{
			throw new ExcepcionNegocio("Esta hospitalizacion no existe");
		}
		
	}
	
	/**
	 * Metodo para buscar una orden de una hospitalizacion
	 * @param id
	 * @return
	 */
	public OrdenHospitalizacion buscar(int id){
		return em.find(OrdenHospitalizacion.class, id);
	}
	
	/**
	 * Metodo para editar una orden de hospitalizacion
	 * @param ordenHospitalizacion
	 */
	public void editar(OrdenHospitalizacion ordenHospitalizacion){
		em.merge(ordenHospitalizacion);
	}

	/**
	 * Metodo para eliminar una orden de una hospitalizacion
	 * @param ordenHospitalizacion
	 */
	public void eliminar(OrdenHospitalizacion ordenHospitalizacion){
		em.remove(ordenHospitalizacion);
	}
}
