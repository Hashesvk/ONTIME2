package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Role;

public interface IPersonaService {
	public boolean registrar(Persona persona);
	public void eliminar(int idPersona);
	public Optional<Persona> listarId(int idPersona);
	public Optional<Persona> buscarId(int idPersona);
	List<Persona> listar();
	List<Role> listarRoles();
}
