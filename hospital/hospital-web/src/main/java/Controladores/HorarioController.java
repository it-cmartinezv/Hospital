package Controladores;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import beans.EJBUsuario;
import beans.HorarioEJB;
import entidades.Horario;
import entidades.Medico;
import excepciones.ExcepcionNegocio;

@Named("horarioController")
@ViewScoped
public class HorarioController implements Serializable{
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	@EJB
	private HorarioEJB horarioEJB;
	
	private Medico medico;
	private String nombre;
	private String dia;
	private List<Horario> horarios;
	private String tarjeta;
	
	/**
	 * Registrar horario
	 */
	public void registrar(){
		try{
			Horario horario = new Horario();
			horario.setDia(dia);
			horarioEJB.crear(horario);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Buscar Medico
	 */
	public void buscarMedico(){
		try{
			if(tarjeta.isEmpty()){
				Messages.addFlashGlobalInfo("Ingrese el numero de tarjeta profesional del medico a buscar");
			}else{
				medico = usuarioEJB.buscarMedicoByTarjeta(tarjeta);
				if(medico != null){
					nombre = medico.getNombre()+" "+medico.getApellido();
					horarios = horarioEJB.horarioByMedico(medico);
				}else{
					Messages.addFlashGlobalInfo("No se ha encontrado ningun medico con el numero de tarjeta profesional "+tarjeta);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Eliminar horario de un medico
	 * @param horario
	 */
	public void eliminar(Horario horario){
		try{
			horarioEJB.eliminar(horario);
			horarios = horarioEJB.horarioByMedico(medico);
			Messages.addFlashGlobalInfo("Se ha eliminado el horario correctamente");
		}catch (ExcepcionNegocio ex) {
			Messages.addGlobalError(ex.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
}
