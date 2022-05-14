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
@Table(name = "Persona")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_persona")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersona;

	@Column(name = "username", nullable = false, length = 15, unique=true)
	private String username;

	@Column(name = "password", nullable = false,  length = 15)
	private String password;

	@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;
	
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Persona(int idPersona, String username, String password, Role role) {
		super();
		this.idPersona = idPersona;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 * @return the idPersona
	 */
	public int getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Role roles) {
		this.role = roles;
	}
	
	public Role getRole() {
		return role;
	}
	

}
