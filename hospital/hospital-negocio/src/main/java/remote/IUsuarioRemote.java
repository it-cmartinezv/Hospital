package remote;

import entidades.Paciente;
import entidades.Persona;

public interface IUsuarioRemote {
	public void registrarPaciente(Paciente paciente);
	public Persona buscarPaciente(Paciente paciente);
}
