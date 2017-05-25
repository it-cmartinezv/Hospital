package beansSeguridad;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import beans.EJBGenerico;
import entidades.Enfermedad;
import entidades.Persona;
import seguridad.Acceso;
import seguridad.Rol;
import seguridad.UsuarioRol;
import util.ConstantesNamedQueries;


/**
 * @author Carlos Martinez
 *
 */
@Stateless
public class SeguridadEJB extends EJBGenerico<Persona>{
	
	/**
	 * constructor.
	 */
	@Override
	public void init() {
		setClase(Persona.class);
	}
	
	/**
	 * Método que lista los roles de un usuario<br>
	 */
//	public List<Rol> listarRolesUsuario(Persona usuario) {
//			return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTARROLESUSUARIO, usuario);
//	}

	/**
	 * Método que lista los roles de un usuario<br>
	 */
	public List<Rol> listarRolesUsuario(Persona usuario) {
		Query q = em.createNamedQuery(UsuarioRol.ROLESBYUSER);
		q.setParameter(1, usuario);
		return q.getResultList();
	}
	
	/**
	 * Método que lista los accesos validos para un rol <br>
	 */
	public List<Acceso> listarAccesosRol(List<Rol> roles) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTARACCESOSROL, roles);
	}
}