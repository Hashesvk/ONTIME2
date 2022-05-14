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
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Evento")
public class Evento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvento; 
	
	
	@NotBlank(message = "Name is mandatory")
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "No puede contener letras especiales")
	@Column(name = "nombreEvento", length = 40, nullable=false)
	private String nombreEvento;

	@ManyToOne
	@JoinColumn(name="idTipovento", nullable=false)
	private TipoEvento tipoEvento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaEvento")
	@FutureOrPresent
	@NotEmpty
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaEvento;

	@Column(name = "descripcionEvento", length = 100, nullable=false)
	private String descripcionEvento;

	@Column(name = "ComplejidadEvento", length = 2, nullable=false)
	private int numcomplejidad ;
	
	@Column(name = "PrioridadEvento", length = 2, nullable=false)
	private int numprioridad ;

	@Column(name = "ImportanciaEvento", length = 2, nullable=false)
	private int numimportancia ;
	
	public Evento() {
		super();
	}
	
	public Evento(int idEvento, String nombreEvento, TipoEvento tpevento, Date fecha, String descripcion, int complejidad, int prioridad, int importancia) {
		super();
		this.idEvento = idEvento;
		this.descripcionEvento = descripcion;
		this.fechaEvento = fecha;
		this.nombreEvento = nombreEvento;
		this.tipoEvento = tpevento;
		this.numcomplejidad = complejidad;
		this.numprioridad = prioridad;
		this.numimportancia = importancia;
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

	public int getNumprioridad() {
		return numprioridad;
	}

	public void setNumprioridad(int numprioridad) {
		this.numprioridad = numprioridad;
	}

	public int getNumimportancia() {
		return numimportancia;
	}

	public void setNumimportancia(int numimportancia) {
		this.numimportancia = numimportancia;
	}

	
	
	
}
