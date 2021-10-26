package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Evento")
public class Evento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvento; 
	
	@Column(name = "nombreEvento", length = 30, nullable=false)
	private String nombreEvento;

	@ManyToOne
	@JoinColumn(name="idTipovento", nullable=false)
	private TipoEvento tipoEvento;
	
	@ManyToOne
	@JoinColumn(name="idPersona", nullable=false)
	private Persona persona;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaEvento")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaEvento;

	@Column(name = "descripcionEvento", length = 60, nullable=false)
	private String descripcionEvento;

	@Column(name = "ComplejidadEvento", length = 30, nullable=false)
	private int numcomplejidad ;

	public Evento() {
		super();
	}
	
	public Evento(int idEvento, String nombreEvento, TipoEvento tpevento, Persona persona, Date fecha, String descripcion, int complejidad) {
		super();
		this.idEvento = idEvento;
		this.descripcionEvento = descripcion;
		this.fechaEvento = fecha;
		this.nombreEvento = nombreEvento;
		this.persona = persona;
		this.tipoEvento = tpevento;
		this.numcomplejidad = complejidad;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Date getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getDescripcionEvento() {
		return descripcionEvento;
	}

	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}

	public int getNumcomplejidad() {
		return numcomplejidad;
	}

	public void setNumcomplejidad(int numcomplejidad) {
		this.numcomplejidad = numcomplejidad;
	}

	
	
	
}
