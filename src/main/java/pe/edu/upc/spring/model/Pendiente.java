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

@Entity
@Table(name="Pendiente")
public class Pendiente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPendiente; 
	
	@NotBlank(message = "Name is mandatory")
	@Column(name = "nombrePendiente", length = 30, nullable=false)
	private String namePendiente;
	
	@Column(name = "nombreTipoPendiente", nullable=false)
	private String nameTipoPendiente;
	
	@Column(name = "nombreEstado", nullable=false)
	private String nameStatus;
	
	@ManyToOne
	@JoinColumn(name="idPersona", nullable=false)
	private Persona persona;

	public Pendiente() {
		super();
	}

	public Pendiente(int idPendiente, String namePendiente, String nameTipoPendiente, String nameStatus,
			Persona persona) {
		super();
		this.idPendiente = idPendiente;
		this.namePendiente = namePendiente;
		this.nameTipoPendiente = nameTipoPendiente;
		this.nameStatus = nameStatus;
		this.persona = persona;
	}

	public int getIdPendiente() {
		return idPendiente;
	}

	public void setIdPendiente(int idPendiente) {
		this.idPendiente = idPendiente;
	}

	public String getNamePendiente() {
		return namePendiente;
	}

	public void setNamePendiente(String namePendiente) {
		this.namePendiente = namePendiente;
	}

	public String getNameTipoPendiente() {
		return nameTipoPendiente;
	}

	public void setNameTipoPendiente(String nameTipoPendiente) {
		this.nameTipoPendiente = nameTipoPendiente;
	}

	public String getNameStatus() {
		return nameStatus;
	}

	public void setNameStatus(String nameStatus) {
		this.nameStatus = nameStatus;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
		
}
