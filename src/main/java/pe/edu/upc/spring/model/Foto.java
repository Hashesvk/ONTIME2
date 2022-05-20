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
@Table(name="Foto")
public class Foto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFoto;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "No puede contener letras especiales")
	@Column(name="nombreFoto",length=80, nullable=false)
	private String namephoto;//acreedor
	
	
	@Column(name="imagen", nullable=false)
	private String  image;
	
	
	
	@ManyToOne
	@JoinColumn(name="idTipoEvento", nullable=false)
	private TipoEvento Tevento;

	public Foto() {
		super();
	}



	public Foto(int idFoto,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "No puede contener letras especiales") String namephoto,
			String image, TipoEvento tevento) {
		super();
		this.idFoto = idFoto;
		this.namephoto = namephoto;
		this.image = image;
		Tevento = tevento;
	}



	public int getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}

	public String getNamephoto() {
		return namephoto;
	}

	public void setNamephoto(String namephoto) {
		this.namephoto = namephoto;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public TipoEvento getTevento() {
		return Tevento;
	}

	public void setTevento(TipoEvento tevento) {
		Tevento = tevento;
	}



	
	
}
