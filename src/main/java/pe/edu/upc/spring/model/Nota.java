package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Nota")
public class Nota implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNota;
	
	@NotBlank(message = "Name is mandatory")
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "No puede contener letras especiales")
	@Column(name="nombreNota", length=40, nullable=false)
	private String nameNota;
	
	@NotBlank(message = "Name is mandatory")	
	@Column(name="descripcionNota", length=100, nullable=false)
	private String descriptionNota;
	
	@ManyToOne
	@JoinColumn(name="idPersona", nullable=false)
	private Persona persona;

	public Nota() {
		super();
	}

	public Nota(int idNota, String nameNota, String descriptionNota, Persona persona) {
		super();
		this.idNota = idNota;
		this.nameNota = nameNota;
		this.descriptionNota = descriptionNota;
		this.persona = persona;
	}

	public int getIdNota() {
		return idNota;
	}

	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}

	public String getNameNota() {
		return nameNota;
	}

	public void setNameNota(String nameNota) {
		this.nameNota = nameNota;
	}

	public String getDescriptionNota() {
		return descriptionNota;
	}

	public void setDescriptionNota(String descriptionNota) {
		this.descriptionNota = descriptionNota;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}	
}
