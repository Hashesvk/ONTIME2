package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Pendiente;
import pe.edu.upc.spring.repository.IPendienteRepository;
import pe.edu.upc.spring.service.IPendienteService;

@Service
public class PendienteServiceImpl implements IPendienteService{
	
	@Autowired
	private IPendienteRepository dPendiente;

	@Override
	@Transactional
	public boolean registrar(Pendiente pendiente) {
		Pendiente objPendiente = dPendiente.save(pendiente);
		if (objPendiente == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPendiente) {
		dPendiente.deleteById(idPendiente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pendiente> listarId(int idPendiente) {
		return dPendiente.findById(idPendiente);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pendiente> buscarId(int idPendiente) {
		return dPendiente.findById(idPendiente);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Pendiente> listar() {
		return dPendiente.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public List<Pendiente> buscarNombre(String namePendiente) {
		return dPendiente.buscarNombre(namePendiente);

	}
	
}
