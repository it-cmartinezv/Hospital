package remote;

import java.util.List;

import entidades.Ciudad;
import entidades.Farmaceutico;
import entidades.Farmacia;

public interface IFarmaciaRemote {
	
	public void crear(Farmacia farmacia);
	public Farmacia buscar (String nombre);
	public void editar (Farmacia farmacia);
	public void eliminar (Farmacia farmacia);
	public List<Ciudad> listaCiudades();
	public List<Farmaceutico> listaFarma();

}
