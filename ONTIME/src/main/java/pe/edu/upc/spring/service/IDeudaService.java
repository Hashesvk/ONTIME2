package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Deuda;
public interface IDeudaService {
	public boolean registrar(Deuda deuda);
	public void eliminar(int idDeuda);
	public Optional<Deuda> listarId(int idDeuda);
	public Optional<Deuda> buscarId(int idDeuda);
	List<Deuda> listar();
	List<Deuda> buscarNombre(String nameCredit);
	List<Deuda> buscarporUsername(String username);
}
