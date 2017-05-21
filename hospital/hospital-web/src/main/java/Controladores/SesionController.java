package Controladores;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import beans.EJBUsuario;

/**
 * 
 * @author Carlos Alfredo Martinez Villada
 * Clase que controla el inicio de sesion en la aplicacion
 * 
 */

@Named("sesionController")
@ViewScoped
public class SesionController implements Serializable{
	
	@EJB
	private EJBUsuario usuarioEJB;
	
	String correo;
	
	String password;
	
	public void entrar(){
		
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
}
