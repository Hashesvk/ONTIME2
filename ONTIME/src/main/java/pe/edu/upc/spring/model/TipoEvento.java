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

@Entity
@Table(name="TipoEvento")
public class TipoEvento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoEvento;
	
	@Column(name="descripcionTipoEvento", length = 30, nullable = false)
	private String descripcionTipoEvento;
	
	@Column(name="nombreTipoEvento", length = 10, nullable = false)
	private String nombreTipoEvento;
	
	@ManyToOne
	@JoinColumn(name="idPersona", nullable=false)
	private Persona persona;

	public TipoEvento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoEvento(int idTipoEvento, String descripcionTipoEvento, String nombreTipoEvento, Persona persona) {
		super();
		this.idTipoEvento = idTipoEvento;
		this.descripcionTipoEvento = descripcionTipoEvento;
		this.nombreTipoEvento = nombreTipoEvento;
		this.persona = persona;
	}

	public int getIdTipoEvento() {
		return idTipoEvento;
	}

	public void setIdTipoEvento(int idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}

	public String getDescripcionTipoEvento() {
		return descripcionTipoEvento;
	}

	public void setDescripcionTipoEvento(String descripcionTipoEvento) {
		this.descripcionTipoEvento = descripcionTipoEvento;
	}

	public String getNombreTipoEvento() {
		return nombreTipoEvento;
	}

	public void setNombreTipoEvento(String nombreTipoEvento) {
		this.nombreTipoEvento = nombreTipoEvento;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	

	
}
