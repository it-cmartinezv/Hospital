package seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Carlos Martinez
 *
 */
@Entity
@Table(name="Acceso")
@NamedQueries({
	@NamedQuery(name=Acceso.LISTA,query="SELECT a FROM Acceso a")
})
public class Acceso implements Serializable{

	public static final String LISTA ="Acceso.LISTA";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_ACCESO") 
	@SequenceGenerator(name="SEQ_ACCESO", sequenceName="SEQ_ACCESO",allocationSize=1) 
	private int id;
		
	@Column(name="nombre",nullable = false,length=100)
	private String nombre;
	
	@Column(name="url",nullable = false,length=300)
	private String url;
	
	public Acceso(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return url;
	}
}