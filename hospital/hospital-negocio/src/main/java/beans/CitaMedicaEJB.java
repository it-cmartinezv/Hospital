package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.CitaMedica;


import entidades.Eps;
import entidades.Medico;
import entidades.Paciente;
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

	 * MEtodo para buscar una cita por su ID este metodo se utiliza principalmente en el converter
	 * @param id
	 * @return
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
	
	/**
	 * citas de un paciente
	 */
	public List<CitaMedica> citasByPaciente(Paciente paciente){
		Query q = em.createNamedQuery(CitaMedica.citasByPaciente);
		q.setParameter(1, paciente);
		return q.getResultList();
	}
	
	/**
	 * citas de un paciente por estado
	 * estados = Pendiente | Cancelada | Atendida
	 */
	public List<CitaMedica> citasByPacienteEstado(Paciente paciente, String estado){
		Query q = em.createNamedQuery(CitaMedica.pacienteByEstado);
		q.setParameter(1, paciente);
		q.setParameter(2, estado);
		return q.getResultList();
	}
	
	/**
	 * citas de un medico
	 */
	public List<CitaMedica> citasByMedico(Medico medico){
		Query q = em.createNamedQuery(CitaMedica.citasByMedico);
		q.setParameter(1, medico);
		return q.getResultList();
	}
	
	/**
	 * citas de un paciente por estado
	 * estados = Pendiente | Cancelada | Atendida
	 */
	public List<CitaMedica> citasByMedicoEstado(Medico medico, String estado){
		Query q = em.createNamedQuery(CitaMedica.medicoByEstado);
		q.setParameter(1, medico);
		q.setParameter(2, estado);
		return q.getResultList();
	}
	
	/**
	 * buscar cita de un paciente
	 */
	public CitaMedica citasByPacienteId(int id, Paciente paciente){
		Query q = em.createNamedQuery(CitaMedica.citasByPacienteID);
		q.setParameter(1, id);
		q.setParameter(2, paciente);
		if(q.getResultList().size() > 0){
			return (CitaMedica) q.getResultList().get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * buscar cita de un medico
	 */
	public CitaMedica citasByMedicoId(int id, Medico medico){
		Query q = em.createNamedQuery(CitaMedica.citasByMedicoID);
		q.setParameter(1, id);
		q.setParameter(2, medico);
		if(q.getResultList().size() > 0){
			return (CitaMedica) q.getResultList().get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * Listar todas las citas
	 * @return, el listado de todas las citas
	 */
	public List<CitaMedica> listarCitasMedicas(){
		Query q = em.createNamedQuery(CitaMedica.LISTA_CITA);
		List<CitaMedica> lista = q.getResultList();
		return lista;
	}
}
