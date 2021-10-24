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
@Table(name="Notificaciones")
public class Notificaciones implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNotificaciones;
	
	@Column(name="nombreNotificacion", length = 30, nullable = false)
	private String nombreNotificacion;
	
	@Column(name="descripcionNotificacion", nullable=false)
	private String desNotifiacion;
	
	@ManyToOne
	@JoinColumn(name="idEvento", nullable=false)
	private Evento evento;

	public Notificaciones() {
		super();
	}
	
	public Notificaciones(int idNotificaciones, String nombre, String descripcion, Evento evento) {
		super();
		this.idNotificaciones = idNotificaciones;
		this.evento = evento;
		this.desNotifiacion = descripcion;
		this.nombreNotificacion = nombre;
	}
	public int getIdNotificaciones() {
		return idNotificaciones;
	}
	public void setIdNotificaciones(int idNotificaciones) {
		this.idNotificaciones = idNotificaciones;
	}
	public String getNombreNotificacion() {
		return nombreNotificacion;
	}
	public void setNombreNotificacion(String nombreNotificacion) {
		this.nombreNotificacion = nombreNotificacion;
	}
	public String getDesNotifiacion() {
		return desNotifiacion;
	}
	public void setDesNotifiacion(String desNotifiacion) {
		this.desNotifiacion = desNotifiacion;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}	
}