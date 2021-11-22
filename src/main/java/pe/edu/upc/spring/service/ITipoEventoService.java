package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.TipoEvento;

public interface ITipoEventoService {
	public boolean registrar(TipoEvento tipoEvento);
	public void eliminar(int idTipoEvento);
	public Optional<TipoEvento> listarId(int idTipoEvento);
	public Optional<TipoEvento> buscarId(int idTipoEvento);
	List<TipoEvento> listar();
	List<TipoEvento> buscarNombre(String nombreTipoEvento, String username);
	List<TipoEvento> buscarporUsername(String username);
}

