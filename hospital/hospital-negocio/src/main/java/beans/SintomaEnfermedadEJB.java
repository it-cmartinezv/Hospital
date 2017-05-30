package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Enfermedad;
import entidades.Sintoma;
import entidades.SintomaEnfermedad;
import entidades.SintomaEnfermedadPK;
import excepciones.ExcepcionNegocio;

/**
 * 
 * @author Sebastian
 *
 */
@LocalBean
@Stateless
public class SintomaEnfermedadEJB {

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private EnfermedadEJB enfermedadEJB;
	
	@EJB
	private SintomaEJB sintomaEJB;
	
	/**
	 * Metodo para crear un sintoma a una enfermedad
	 * @param sintomaEnfermedad
	 */
	public void crear(SintomaEnfermedad sintomaEnfermedad) throws ExcepcionNegocio{
		Enfermedad enfermedad = enfermedadEJB.buscar(sintomaEnfermedad.getEnfermedad().getId());
		if(enfermedad != null){
			Sintoma sintoma = sintomaEJB.buscar(sintomaEnfermedad.getSintoma().getId());
			if(sintoma != null){
				SintomaEnfermedad buscar = buscar(sintomaEnfermedad.getEnfermedad(), sintomaEnfermedad.getSintoma());
				if(buscar == null){
					sintomaEnfermedad.setEnfermedad(enfermedad);
					sintomaEnfermedad.setSintoma(sintoma);
					em.persist(sintomaEnfermedad);
				}else{
					throw new ExcepcionNegocio("Ya registraste un sintoma a esta enfermedad");
				}
			}else{
				throw new ExcepcionNegocio("Sintoma no existe");
			}
		}else{
			throw new ExcepcionNegocio("Enfermedad no existe");
		}
	}
	
	/**
	 * Buscar los sintomas de una enfermedad
	 * @param enfermedad, la enfermedad
	 * @param sintoma, el sintoma
	 * @return
	 */
	public SintomaEnfermedad buscar(Enfermedad enfermedad, Sintoma sintoma){
		SintomaEnfermedadPK pk = new SintomaEnfermedadPK();
		pk.setEnfermedad(enfermedad.getId());
		pk.setSintoma(sintoma.getId());
		return em.find(SintomaEnfermedad.class, pk);
	}
	
	/**
	 * Listar sintomas de una enfermedad
	 * @param enfermedad
	 * @return
	 */
	public List<SintomaEnfermedad> listarSintomaByEnfermedad(Enfermedad enfermedad){
		Query q = em.createNamedQuery(SintomaEnfermedad.BYENFERMEDAD);
		q.setParameter(1, enfermedad);
		return q.getResultList();
	}
	
	public List<SintomaEnfermedad> listar(){
		Query q = em.createNamedQuery(SintomaEnfermedad.LISTAR);
		return q.getResultList();
	}
}
