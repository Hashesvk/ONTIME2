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
	private TipoEvento tipoevento;
	
	
	
}
