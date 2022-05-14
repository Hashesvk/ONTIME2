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
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;




@Entity
@Table(name="Prueba")
public class Prueba implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPrueba;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "No puede contener letras especiales")
	@Column(name="nombrePrueba", length=30, nullable=false)
	private String namePrueba;//acreedor
	
	@PositiveOrZero 	
	@Column(name="NumNota", nullable=false)
	private float  NumberGrade;
	
	@PositiveOrZero 	
	@Column(name="NumPonderado", nullable=false)
	private float  Numberweighted;
	
	
	@ManyToOne
	@JoinColumn(name="idTipoEvento", nullable=false)
	private TipoEvento Tevento;

	public Prueba() {
		super();
	}

	public Prueba(int idPrueba,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "No puede contener letras especiales") String namePrueba,
			float numberGrade, float numberweighted, TipoEvento tevento) {
		super();
		this.idPrueba = idPrueba;
		this.namePrueba = namePrueba;
		NumberGrade = numberGrade;
		Numberweighted = numberweighted;
		Tevento = tevento;
	}

	public int getIdPrueba() {
		return idPrueba;
	}

	public void setIdPrueba(int idPrueba) {
		this.idPrueba = idPrueba;
	}

	public String getNamePrueba() {
		return namePrueba;
	}

	public void setNamePrueba(String namePrueba) {
		this.namePrueba = namePrueba;
	}

	public float getNumberGrade() {
		return NumberGrade;
	}

	public void setNumberGrade(float numberGrade) {
		NumberGrade = numberGrade;
	}

	public float getNumberweighted() {
		return Numberweighted;
	}

	public void setNumberweighted(float numberweighted) {
		Numberweighted = numberweighted;
	}

	public TipoEvento getTevento() {
		return Tevento;
	}

	public void setTevento(TipoEvento tevento) {
		Tevento = tevento;
	}

	

	
	
}
