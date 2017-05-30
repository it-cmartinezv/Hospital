package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@LocalBean
@Stateless
public class ReporteEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<String> CITAS_BY_MEDICO_CIUDAD(){
		Query q = em.createNativeQuery("SELECT * FROM REPORTES_CARLOS_MARTINEZ");
		return q.getResultList();
	}
}
