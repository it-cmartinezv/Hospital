package Controladores;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

import beans.EnfermedadEJB;
import entidades.Enfermedad;

/**
 * 
 * @author Sebastian
 *
 */
@ViewScoped
@Named("enfermedadController")
public class EnfermedadController implements Serializable {
	
	@EJB
	private EnfermedadEJB enfermedadEJB;
	
	@Pattern(regexp="[a-zA-Z ]*",message="Nombre No valido")
	@Length(min=4,max=50,message="longitud entre 4 y 50")
	String nombre;
	
	

}
