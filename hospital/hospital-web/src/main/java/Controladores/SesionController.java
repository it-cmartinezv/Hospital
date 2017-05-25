package Controladores;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import beans.EJBUsuario;
import entidades.Persona;

@Named("sesionController2")
@SessionScoped
public class SesionController implements Serializable{
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	private String correo;
	private String password;
	private Persona persona;
	
	/**
	 * Iniciar sesion en el aplicativo
	 */
	public String entrar(){
		Persona usuario = usuarioEJB.usuarioByCorreo(correo);
		if(usuario != null){
			if(usuario.getPassword().equals(password)){
				persona = usuario;
//				switch (usuario.getRol().getId()) {
//				case 1:
//					/* 1 Administrador */
//					Faces.setSessionAttribute("usuario", usuario);
//					return "/paginas/seguro/administrador.xhtml?faces-redirect=true";
//				case 2:
//					/* 2 Medico */
//					Faces.setSessionAttribute("usuario", usuario);
//					return "/paginas/seguro/medico.xhtml?faces-redirect=true";
//				case 3:
//					/* 3 Farmaceutico */
//					Faces.setSessionAttribute("usuario", usuario);
//					return "/paginas/seguro/farmaceutico.xhtml?faces-redirect=true";
//					
//				case 4:
//					/* 4 Paciente */
//					Faces.setSessionAttribute("usuario", usuario);
//					return "/paginas/seguro/paciente.xhtml?faces-redirect=true";
//				default:
//					Messages.addFlashGlobalError("Ingrese con otras credenciales");
//					break;
//				}
			}else{
				Messages.addFlashGlobalError("Correo o password incorrectos");
			}
		}else{
			Messages.addFlashGlobalError("Correo o password incorrectos");
		}
		return null;
	}
	
	
	public String cerrarSesion() {
		persona = null;
		HttpSession sesion;
		sesion = (HttpSession) Faces.getSession();
		sesion.invalidate();
		return "/paginas/publico/iniciar-sesion.xhtml?faces-redirect=true";
	}

	public boolean isSesion() {
		return persona != null;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
