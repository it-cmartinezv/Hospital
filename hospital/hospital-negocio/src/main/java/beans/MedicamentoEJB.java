package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entidades.Farmacia;
import entidades.Medicamento;
import entidades.TipoMedicamento;

/**
 * @author AlejandroM
 *
 */
@LocalBean
@Stateless
public class MedicamentoEJB {

	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo para crear un medicamento
	 * @param medicamento
	 */
	public void crear(Medicamento medicamento){
		em.persist(medicamento);
	}
	
	/**
	 * Metodo para buscar un medicamento
	 * @param id, el id del medicamento por el cual se va a buscar
	 * @return el medicamento si se encuenta de lo contrario sale null
	 */
	public Medicamento buscar(int id){
		return em.find(Medicamento.class, id);
	}
	
	/**
	 * Metodo para editar un medicamento
	 * @param medicamento
	 */
	public void editar(Medicamento medicamento){
		em.merge(medicamento);
	}
	
	/**
	 * Metodo para eliminar un medicamento
	 * @param medicamento
	 */
	public void eliminar(Medicamento medicamento){
		em.remove(medicamento);
	}
	
	/**
	 * Lista que me trae todos los tipos de medicamentos que existen
	 * @return la lista con los tipos que se encontraron
	 */
	public List<TipoMedicamento>listarTipoMedicamentos(){
		Query q = em.createNamedQuery(TipoMedicamento.listar);
		List<TipoMedicamento> lista = q.getResultList();
		return lista;
	}
	
	/**
	 * MEtodo que me trae todas las farmacias que se encuentran en el regisro
	 * @return lista de farmacias
	 */
	public List<Farmacia> listaFarmacia(){
		Query q = em.createNamedQuery(Farmacia.listaFarmacias);
		List<Farmacia> lista = q.getResultList();
		
		return lista;
	}
	
}
