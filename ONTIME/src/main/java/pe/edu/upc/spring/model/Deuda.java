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
@Table(name="Deuda")
public class Deuda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDeuda;
	
	@Column(name="nombreDeuda", length=30, nullable=false)
	private String nameCreditor;//acreedor
	
	@Column(name="pagoDinero", nullable=false)
	private float  moneypayment;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechaPago")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date paymentDate;
	
	//@Column(name="pagoRealizado", nullable=false)
	//private boolean  paymentMade;
	
	@ManyToOne
	@JoinColumn(name="idPersona", nullable=false)
	private Persona persona;

	public Deuda() {
		super();
	}

	public Deuda(int idDeuda, String nameCreditor, float moneypayment, Date paymentDate, //boolean paymentMade,
			Persona persona) {
		super();
		this.idDeuda = idDeuda;
		this.nameCreditor = nameCreditor;
		this.moneypayment = moneypayment;
		this.paymentDate = paymentDate;
		//this.paymentMade = paymentMade;
		this.persona = persona;
	}

	public int getIdDeuda() {
		return idDeuda;
	}

	public void setIdDeuda(int idDeuda) {
		this.idDeuda = idDeuda;
	}

	public String getNameCreditor() {
		return nameCreditor;
	}

	public void setNameCreditor(String nameCreditor) {
		this.nameCreditor = nameCreditor;
	}

	public float getMoneypayment() {
		return moneypayment;
	}

	public void setMoneypayment(float moneypayment) {
		this.moneypayment = moneypayment;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	//public boolean isPaymentMade() {
	//	return paymentMade;
	//}

	//public void setPaymentMade(boolean paymentMade) {
	//	this.paymentMade = paymentMade;
	//}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	
	
}
