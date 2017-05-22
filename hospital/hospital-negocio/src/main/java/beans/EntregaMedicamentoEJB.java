/**
 * 
 */
package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.EntregaMedicamento;
import entidades.Medicamento;

/**
 * @author AlejandroM
 *
 */
@LocalBean
@Stateless
public class EntregaMedicamentoEJB {

	

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo que entregara un medicamento a un paciente
	 * @param entregaMedicamento
	 */
	public void entregarMedicamento(EntregaMedicamento entregaMedicamento){
		em.persist(entregaMedicamento);
	}
	
	/**
	 * Metodo encargado de traer todos las entregas realizadas
	 * @return
	 */
	public List<EntregaMedicamento> listaEntregas(){
		Query q = em.createNamedQuery(EntregaMedicamento.entregasListar);
		List<EntregaMedicamento> lista = q.getResultList();
		return lista;
		
	}
	
	
}
