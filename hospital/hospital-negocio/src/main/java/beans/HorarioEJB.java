package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.CitaMedica;
import entidades.Horario;
import entidades.Medico;
import entidades.Paciente;

@LocalBean
@Stateless
public class HorarioEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Crear
	 */
	public void crear(Horario entidad) {
		em.persist(entidad);
	}
	
	/**
	 * Buscar
	 */
	public Horario buscar(int id) {
		return em.find(Horario.class, id);
	}
	
	/**
	 * Editar
	 */
	public void editar(Horario entidad) {
		em.merge(entidad);
	}
	
	/**
	 * Eliminar
	 */
	public void eliminar(Horario entidad) {
		Horario horario = buscar(entidad.getId());
		if (horario != null) {
			em.remove(horario);
		} else {
			throw new excepciones.ExcepcionNegocio("No se ha encontrado ningun horario");
		}

	}
	
	/**
	 * Lista de horarios de un medico
	 */
	public List<Horario> horarioByMedico(Medico medico){
		Query q = em.createNamedQuery(Horario.BYMEDICO);
		q.setParameter(1, medico);
		return q.getResultList();
	}
}
