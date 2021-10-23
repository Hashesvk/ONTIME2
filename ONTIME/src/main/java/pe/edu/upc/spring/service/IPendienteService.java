package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Pendiente;

public interface IPendienteService {
	public boolean registrar(Pendiente pendiente);
	public void eliminar(int idPendiente);
	public Optional<Pendiente> listarId(int idPendiente);
	public Optional<Pendiente> buscarId(int idPendiente);
	List<Pendiente> listar();
	List<Pendiente> buscarNombre(String namePendiente);
}