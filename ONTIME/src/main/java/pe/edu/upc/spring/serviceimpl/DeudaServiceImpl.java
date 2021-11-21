package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Deuda;
import pe.edu.upc.spring.repository.IDeudaRepository;
import pe.edu.upc.spring.service.IDeudaService;

@Service
public class DeudaServiceImpl implements IDeudaService {

	@Autowired
	private IDeudaRepository dDeuda;
	
	@Override
	@Transactional
	public boolean registrar(Deuda deuda) {
		Deuda objDeuda = dDeuda.save(deuda);
		if (objDeuda == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idDeuda) {
		dDeuda.deleteById(idDeuda);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Deuda> listarId(int idDeuda) {
		return dDeuda.findById(idDeuda);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Deuda> buscarId(int idDeuda) {
		return dDeuda.findById(idDeuda);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Deuda> listar() {
		return dDeuda.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Deuda> buscarNombre(String nameCredit) {
		return dDeuda.buscarNombre(nameCredit);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Deuda> buscarporUsername(String username) {
		return dDeuda.findByPersonaUsername(username);

	}
	
}
