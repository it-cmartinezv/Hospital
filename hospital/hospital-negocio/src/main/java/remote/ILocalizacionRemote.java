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
	public List<Departamento> listarDepartamentos();
	public List<Ciudad> listarCiudades();
}
