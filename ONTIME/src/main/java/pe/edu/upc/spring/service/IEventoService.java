package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Evento;

public interface IEventoService {
	public boolean registrar(Evento evento);
	public void eliminar(int idEvento);
	public Optional<Evento> listarId(int idEvento);
	public Optional<Evento> buscarId(int idEvento);
	List<Evento> listar();
	List<Evento> buscarNombre(String nameEvento);
	List<Evento> buscarComple(int numcomplejidad);
	List<Evento> buscarTevento(String nombreTEvento);
	List<Evento> buscarporUsername(String username);
	//List<Evento> buscarPriori(String numprioridad);
	//List<Evento> buscarImpor(String numimportancia);



}