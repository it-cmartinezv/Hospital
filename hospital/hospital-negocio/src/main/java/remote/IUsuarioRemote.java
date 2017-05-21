package remote;

import entidades.Farmaceutico;
import entidades.Medico;
import entidades.Paciente;
import entidades.Persona;

public interface IUsuarioRemote {
	public Persona buscarUsuario(String tipoId, String numeroId);
	public Persona usuarioByTelefono(String telefono);
	public Persona usuarioByCorreo(String correo);
	public void registrarPaciente(Paciente paciente);
	public void registrarMedico(Medico medico);
	public Medico buscarMedico(String tipoId, String numId);
	public Medico buscarMedicoByTarjeta(String tarjetaProfesional);
	public void registrarFarmaceutico(Farmaceutico farmaceutico);
	public Paciente buscarPaciente(String tipoid, String numid);
}
