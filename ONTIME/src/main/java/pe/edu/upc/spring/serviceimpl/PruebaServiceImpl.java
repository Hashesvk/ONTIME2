package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Prueba;
import pe.edu.upc.spring.repository.IPruebaRepository;
import pe.edu.upc.spring.service.IPruebaService;

@Service
public class PruebaServiceImpl implements IPruebaService {

	@Autowired
	private IPruebaRepository dPrueba;
	
	@Override
	@Transactional
	public boolean registrar(Prueba prueba) {
		Prueba objPrueba = dPrueba.save(prueba);
		if (objPrueba == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPrueba) {
		dPrueba.deleteById(idPrueba);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Prueba> listarId(int idPrueba) {
		return dPrueba.findById(idPrueba);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Prueba> buscarId(int idPrueba) {
		return dPrueba.findById(idPrueba);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prueba> listar() {
		return dPrueba.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prueba> buscarNombre(String namePrub) {
		return dPrueba.buscarNombre(namePrub);
	}

}
