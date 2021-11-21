package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Nota;
import pe.edu.upc.spring.repository.INotaRepository;
import pe.edu.upc.spring.service.INotaService;

@Service
public class NotaServiceImpl implements INotaService {

	@Autowired
	private INotaRepository dNota;
	
	@Override
	@Transactional
	public boolean registrar(Nota nota) {
		Nota objNota = dNota.save(nota);
		if (objNota == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idNota) {
		dNota.deleteById(idNota);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Nota> listarId(int idNota) {
		return dNota.findById(idNota);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Nota> buscarId(int idNota) {
		return dNota.findById(idNota);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Nota> listar() {
		return dNota.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Nota> buscarNombre(String nameNota) {
		return dNota.buscarNombre(nameNota);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Nota> buscarporUsername(String username) {
		return dNota.findByPersonaUsername(username);

	}
}
