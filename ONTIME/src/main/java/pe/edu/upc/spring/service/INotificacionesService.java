package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Notificaciones;

public interface INotificacionesService {
	public boolean registrar(Notificaciones Notificaciones);
	public void eliminar(int idNotificaciones);
	public Optional<Notificaciones> listarId(int idNotificaciones);
	public Optional<Notificaciones> buscarId(int idNotificaciones);
	List<Notificaciones> listar();
	List<Notificaciones> buscarNombre(String nombreNotificaciones);
}
