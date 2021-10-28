package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Notificacion;

public interface INotificacionService {
	public boolean registrar(Notificacion notificacion);
	public void eliminar(int idNotificacion);
	public Optional<Notificacion> listarId(int idNotificacion);
	public Optional<Notificacion> buscarId(int idNotificacion);
	List<Notificacion> listar();
	List<Notificacion> buscarNombre(String nombreNotificacion);
}
