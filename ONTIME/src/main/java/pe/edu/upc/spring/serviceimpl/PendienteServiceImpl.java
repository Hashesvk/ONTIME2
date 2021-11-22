package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
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
	public List<Pendiente> buscarNombre(String namePendiente, String username) {
		List<Pendiente>pendiente0=dPendiente.findByPersonaUsername(username);
		List<Pendiente>pendiente2=dPendiente.buscarNombre(namePendiente);
		List<Pendiente>pendiente3= new ArrayList<Pendiente>();
		
		for(int i =0 ; i<pendiente0.size();i++) {
			for(int j =0 ; j< pendiente2.size(); j++) {
			if(pendiente0.get(i) == pendiente2.get(j)){
				pendiente3.add(pendiente0.get(i));			
			}
			}	
		}
		return pendiente3;
		

	}
	@Override
	@Transactional(readOnly = true)
	public List<Pendiente> buscarporUsername(String username) {
		return dPendiente.findByPersonaUsername(username);

	}
}
