package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Nota;

public interface INotaService {
	public boolean registrar(Nota nota);
	public void eliminar(int idNota);
	public Optional<Nota> listarId(int idNota);
	public Optional<Nota> buscarId(int idNota);
	List<Nota> listar();
	List<Nota> buscarporUsername(String username);
	List<Nota> buscarNombre(String nameNota);
}
