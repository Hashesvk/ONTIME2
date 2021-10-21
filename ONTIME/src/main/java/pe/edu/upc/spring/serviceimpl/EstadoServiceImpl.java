package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Estado;
import pe.edu.upc.spring.repository.IEstadoRepository;
import pe.edu.upc.spring.service.IEstadoService;

public class EstadoServiceImpl implements IEstadoService {
	
	@Autowired
	private IEstadoRepository dEstado;
	
	@Override
	@Transactional
	public boolean registrar(Estado estado) {
		Estado objEstado = dEstado.save(estado);
		if (objEstado == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Estado> listar() {
		return dEstado.findAll();
	}
	
}
