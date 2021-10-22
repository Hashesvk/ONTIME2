package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Foto;

public interface IFotoService {
	public boolean registrar(Foto foto);
	public void eliminar(int idFoto);
	public Optional<Foto> listarId(int idFoto);
	public Optional<Foto> buscarId(int idFoto);
	List<Foto> listar();
	List<Foto> buscarNombre(String namePho);
}
