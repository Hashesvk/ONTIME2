package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.TipoEvento;
import pe.edu.upc.spring.repository.ITipoEventoRepository;
import pe.edu.upc.spring.service.ITipoEventoService;

public class TipoEventoServiceImpl implements ITipoEventoService {
	
	@Autowired
	private ITipoEventoRepository dtipoevento;
	
	@Override
	@Transactional
	public boolean registrar(TipoEvento tipoEvento) {
		TipoEvento objTipoEvento = dtipoevento.save(tipoEvento);
		if (objTipoEvento == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idTipoEvento) {
		dtipoevento.deleteById(idTipoEvento);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<TipoEvento> listarId(int idTipoEvento) {
		return dtipoevento.findById(idTipoEvento);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<TipoEvento> buscarId(int idTipoEvento) {
		return dtipoevento.findById(idTipoEvento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoEvento> listar() {
		return dtipoevento.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoEvento> buscarNombre(String nombreTipoEvento) {
		return dtipoevento.buscarNombre(nombreTipoEvento);
	}

	
}
