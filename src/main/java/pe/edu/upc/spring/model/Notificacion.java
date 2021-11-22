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
@Table(name="Notificacion")
public class Notificacion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNotificacion;
	
	@Column(name="nombreNotificacion", length = 30, nullable = false)
	private String nameNotificacion;
	
	@Column(name="descripcionNotificacion", nullable=false)
	private String descriptionNotificacion;
	
	@ManyToOne
	@JoinColumn(name="idEvento", nullable=false)
	private Evento evento;

	public Notificacion() {
		super();
	}

	public Notificacion(int idNotificacion, String nameNotificacion, String descriptionNotificacion, Evento evento) {
		super();
		this.idNotificacion = idNotificacion;
		this.nameNotificacion = nameNotificacion;
		this.descriptionNotificacion = descriptionNotificacion;
		this.evento = evento;
	}

	public int getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public String getNameNotificacion() {
		return nameNotificacion;
	}

	public void setNameNotificacion(String nameNotificacion) {
		this.nameNotificacion = nameNotificacion;
	}

	public String getDescriptionNotificacion() {
		return descriptionNotificacion;
	}

	public void setDescriptionNotificacion(String descriptionNotificacion) {
		this.descriptionNotificacion = descriptionNotificacion;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}	
	

}