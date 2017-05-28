package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.CitaMedica;
import entidades.Eps;
import entidades.Medicamento;
import entidades.OrdenMedicamento;
import entidades.TipoEps;

@LocalBean
@Stateless
public class OrdenMedicamentoEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Metodo para registrar una entrega del medicamento
	 * @param entregaMedicamento
	 */
	public void registrarOrden(OrdenMedicamento ordenMedicamento){
		em.persist(ordenMedicamento);
	}

	/**
	 * Metodo que deberia buscar por un ID de la clase 
	 * @param id, el id a buscar pero REVISAR 
	 * @return
	 */
	public OrdenMedicamento buscar(int id){
	return	em.find(OrdenMedicamento.class, id);
	}
	
	/**
	 * Este metodo es para buscar una orden de medicamento y la va  abuscar por su ID
	 * @param id
	 * @return
	 */
	public OrdenMedicamento buscarId(int id){
		Query q = em.createNamedQuery(OrdenMedicamento.BUSCARPORID);
		q.setParameter(1, id);
		List<OrdenMedicamento> lista = q.getResultList();
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
		
	}
	
	/**
	 * Metodo que va a editar la orden de un medicamento
	 * @param ordenMedicamento
	 */
	public void editar(OrdenMedicamento ordenMedicamento){
		
		em.merge(ordenMedicamento);
		
	}
	
	/**
	 * Metodo para eliminar una orden de un medicamento
	 * @param ordenMedicamento
	 */
	public void eliminar(OrdenMedicamento ordenMedicamento){
		em.remove(ordenMedicamento);
		
	}
	
	/**
	 * Lista que me trae las citas medicas
	 * @return
	 */
	public List<CitaMedica> listaCitas(){
		Query q = em.createNamedQuery(CitaMedica.LISTA_CITA);
		List<CitaMedica> lista = q.getResultList();
		return lista;
	}
		
	/**
	 * Metodo para listar las listas que tiene un medicamento
	 * @return
	 */
	public List<OrdenMedicamento> listaOrden(){
		Query q = em.createNamedQuery(OrdenMedicamento.LISTARORDENES);
		List<OrdenMedicamento> lista = q.getResultList();
		return lista;
	}
	
}
