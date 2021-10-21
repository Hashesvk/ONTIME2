package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Persona")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPersona;
	
	@Column(name="nombrePersona", nullable=false, length=15)
	private String nombrePersona;
	
	@Column(name="contraseñaPersona", nullable=false, length=20)
	private String contraseñaPersona;
	
	@Column(name="tipoPersona", nullable=false)
	private char tipoPersona;
	
	@Column(name="sueldoPersona")
	private float sueldoPersona;

	public Persona() {
		super();
	}

	public Persona(int idPersona, String nombrePersona, String contraseñaPersona, char tipoPersona,
			float sueldoPersona) {
		super();
		this.idPersona = idPersona;
		this.nombrePersona = nombrePersona;
		this.contraseñaPersona = contraseñaPersona;
		this.tipoPersona = tipoPersona;
		this.sueldoPersona = sueldoPersona;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public String getContraseñaPersona() {
		return contraseñaPersona;
	}

	public void setContraseñaPersona(String contraseñaPersona) {
		this.contraseñaPersona = contraseñaPersona;
	}

	public char getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(char tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public float getSueldoPersona() {
		return sueldoPersona;
	}

	public void setSueldoPersona(float sueldoPersona) {
		this.sueldoPersona = sueldoPersona;
	}
	
}
