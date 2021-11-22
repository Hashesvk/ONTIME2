package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Prueba;

public interface IPruebaService {
	public boolean registrar(Prueba prueba);
	public void eliminar(int idPrueba);
	public Optional<Prueba> listarId(int idPrueba);
	public Optional<Prueba> buscarId(int idPrueba);
	List<Prueba> listar();
	List<Prueba> buscarNombre(String namePrueba);
	List<Prueba> buscarTevento(String nombreTevento);
	List<Prueba> buscarporUsername(String username);
}
