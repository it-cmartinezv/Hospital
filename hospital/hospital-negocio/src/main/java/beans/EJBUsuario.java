package beans;
import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import entidades.Ciudad;
import entidades.Paciente;
import entidades.Persona;
import remote.ILocalizacionRemote;
import remote.IUsuarioRemote;

/**
 * 
 * @author carlos martinez
 * clase encargada de las operaciones de usuario
 */
@LocalBean
@Stateless
@Remote(IUsuarioRemote.class)
public class EJBUsuario implements Serializable{
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Buscar ciudad por id
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Persona buscarPaciente(Paciente paciente){
		Query q = em.createNamedQuery(Persona.BUSCAR);
		q.setParameter(1, paciente.getNumeroIdentificacion());
		q.setParameter(2, paciente.getTipoIdentificacion());
		List<Persona> lista = q.getResultList();
		if(lista.size() > 0){
			return lista.get(0);
		}
		return null;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarPaciente(Paciente paciente){
		Persona persona = buscarPaciente(paciente);
		if(persona == null){
			em.persist(paciente);
		}else{
			throw new excepciones.ExcepcionNegocio("Ya existe este Cliente");
		}
		
	}
}
