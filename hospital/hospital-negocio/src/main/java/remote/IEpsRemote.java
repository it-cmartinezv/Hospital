package remote;
import java.util.List;
import entidades.Eps;
import entidades.TipoEps;

public interface IEpsRemote {
	
	public void crear (Eps eps);
	public Eps buscar(int id);
	public void editar(Eps eps);
	public void eliminar(Eps eps);
	public List<TipoEps> listaTipos();
	public List<Eps> listar();
	
}
