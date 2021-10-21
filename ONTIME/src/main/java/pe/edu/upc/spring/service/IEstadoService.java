package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Estado;

public interface IEstadoService {
	public boolean registrar(Estado estado);
	List<Estado> listar();
}

