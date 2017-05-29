package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidades.CitaMedica;
import entidades.CitaSintoma;
import entidades.CitaSintomaPK;
import entidades.Sintoma;
import excepciones.ExcepcionNegocio;

@LocalBean
@Stateless
public class CitaSintomaEJB {
	
	@EJB
	private CitaMedicaEJB citaMedicaEJB;
	
	@EJB
	private SintomaEJB sintomaEJB;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * listar sintomas de una cita
	 */
	public List<CitaSintoma> listarSintomasByCita(CitaMedica cita){
		Query q = em.createNamedQuery(CitaSintoma.BYCITA);
		q.setParameter(1, cita);
		return q.getResultList();
	}
	
	/**
	 * Buscar cita
	 */
	public CitaSintoma buscar(CitaMedica cita, Sintoma sintoma){
		CitaSintomaPK pk = new CitaSintomaPK();
		pk.setCita(cita.getId());
		pk.setSintoma(sintoma.getId());
		return em.find(CitaSintoma.class, pk);
	}
	
	/**
	 * Crear
	 */
	public void crear(CitaSintoma citaSintoma) {
		CitaMedica cita = citaMedicaEJB.buscar(citaSintoma.getCita().getId());
		if(cita != null){
			Sintoma sintoma = sintomaEJB.buscar(citaSintoma.getSintoma().getId());
			if(sintoma != null){
				CitaSintoma buscar = buscar(citaSintoma.getCita(), citaSintoma.getSintoma());
				if(buscar == null){
					citaSintoma.setCita(cita);
					citaSintoma.setSintoma(sintoma);
					em.persist(citaSintoma);
				}else{
					throw new ExcepcionNegocio("Ya registraste este sintoma para esta cita medica");
				}
			}else{
				throw new ExcepcionNegocio("Sintoma no existe");
			}
		}else{
			throw new ExcepcionNegocio("Cita medica no existe");
		}
	}
	
	/**
	 * Editar
	 */
	public void editar(CitaSintoma citaSintoma) {
		em.merge(citaSintoma);
	}
}
