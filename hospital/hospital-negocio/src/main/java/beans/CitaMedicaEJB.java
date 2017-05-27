package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.CitaMedica;

import entidades.Eps;
import entidades.Quirofano;

import entidades.Sintoma;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class CitaMedicaEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo que lista todos los sintomas al momento de la cita
	 * @return, lista de todos los sintomas
	 */
	public List<Sintoma> listarSintomas(){
		Query q = em.createNamedQuery(Sintoma.listarSintomas);
		List<Sintoma> lista = q.getResultList();
		return lista;
	}
	
	/**

	 * Buscar cita
	 */
	public CitaMedica buscar(int id){
		return em.find(CitaMedica.class, id);
	}
	
	/**
	 * Crear
	 */
	public void crear(CitaMedica cita) {
		em.persist(cita);
	}
	
	/**
	 * Editar
	 */
	public void editar(CitaMedica cita) {
		em.merge(cita);
	}

	

}
