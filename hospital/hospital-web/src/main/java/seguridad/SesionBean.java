package seguridad;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import beans.EJBUsuario;
import entidades.Persona;

@Named("sesionController")
@SessionScoped
public class SesionBean implements Serializable {

	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SesionBean.class);

	private Persona usuario;
	
	@Email
	private String correo;
	
	@Length(min=1,max=200,message="Ingresa una contraseña")
	private String pass;

	@EJB
	private beansSeguridad.SeguridadEJB seguridadEJB;
	
	@EJB
	private EJBUsuario personaEJB;

	private List<Acceso> accesos;

	private List<Rol> roles;

	/**
	 * Método que inicia sesion en el aplicativo
	 * @version 1.0
	 * @return
	 *
	 */
	public String entrar() {
		Persona persona = personaEJB.usuarioByCorreo(correo);
		if (persona != null && persona.getPassword().equals(pass)) {
			usuario = persona;
			Faces.getSession().setAttribute("usuario", usuario);
			roles = seguridadEJB.listarRolesUsuario(usuario);
			accesos = seguridadEJB.listarAccesosRol(roles);
			Messages.addFlashGlobalInfo("Bienvenido "+usuario.getNombre()+" "+usuario.getApellido()+", su ingreso ha sido exitoso");
			return "/jsf?faces-redirect=true";
		} else {
			usuario = null;
			roles = null;
			accesos = null;
			Messages.addFlashGlobalError("Por favor, ingrese un correo y contraseña validos");
			return null;
		}

	}

	/**
	 * 
	 * Método que valida si hay sesion
	 */
	public boolean isLogged() {
		return usuario != null;
	}

	public boolean isSesion(){
		return usuario!=null;
	}
	
	public String cerrarSesion() {
		usuario=null;
		HttpSession sesion;
		sesion=(HttpSession)Faces.getSession();
		sesion.invalidate();
		return "/paginas/publico/iniciar-sesion.xhtml?faces-redirect=true";
//		//
//		// FacesContext.getCurrentInstance().getExternalContext().getSession().invalidate()
//		Faces.getSession().invalidate();
//		return "/?faces-redirect=true";
	}

	public org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public void setLogger(org.apache.log4j.Logger logger) {
		this.logger = logger;
	}

	public Persona getUsuario() {
		return usuario;
	}

	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Acceso> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
}
