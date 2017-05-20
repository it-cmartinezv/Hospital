package remote;
import java.util.List;
import entidades.Ciudad;
import entidades.Departamento;
import entidades.Pais;

/**
 * Interface remota para acceder a las de LocalizacionEJB.
 * 
 */
public interface ILocalizacionRemote {
	
	public List<Pais> listarPaises();
	public Pais buscarPais(int id);
	public List<Departamento> listarDepartamentos();
	public List<Departamento> departamentosByPais(Pais pais);
	public Departamento buscarDepartamento(int id);
	public List<Ciudad> listarCiudades();
	public List<Ciudad> ciudadesByDepartamento(Departamento departamento);
	public Ciudad buscarCiudad(int id);
}
