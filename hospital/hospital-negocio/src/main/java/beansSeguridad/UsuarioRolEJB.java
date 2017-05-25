package beansSeguridad;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidades.Departamento;
import entidades.Persona;
import seguridad.UsuarioRol;

@LocalBean
@Stateless
public class UsuarioRolEJB {
	@PersistenceContext
	private EntityManager em;
	
	/**
	 *crear
	 */
	public void crear(UsuarioRol usuarioRol){
		em.persist(usuarioRol);
	}
	
	/**
	 * listado de personas con un determinado rol
	 * @param rol el rol que debe tener el listado de personas
	 * @return personas que tengan un rol
	 */
	public List<Persona> listarPersonasByRol(int rol){
		Query q = em.createNamedQuery(UsuarioRol.PERSONASBYROL);
		q.setParameter(1, rol);
		return q.getResultList();
	}
}
