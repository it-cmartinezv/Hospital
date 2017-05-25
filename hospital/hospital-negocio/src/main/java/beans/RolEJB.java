package beans;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seguridad.Rol;
/**
 * @author carlos
 *
 */
@LocalBean
@Stateless
public class RolEJB {

	@PersistenceContext
	private EntityManager em;
	

	public Rol buscar(int id){
		return em.find(Rol.class, id);
	}
}
