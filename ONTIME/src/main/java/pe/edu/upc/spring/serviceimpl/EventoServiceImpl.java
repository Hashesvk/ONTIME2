package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Evento;
import pe.edu.upc.spring.repository.IEventoRepository;
import pe.edu.upc.spring.service.IEventoService;

@Service
public class EventoServiceImpl implements IEventoService{
	
	@Autowired
	private IEventoRepository dEvento;

	@Override
	@Transactional
	public boolean registrar(Evento evento) {
		Evento objEvento = dEvento.save(evento);
		if (objEvento == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idEvento) {
		dEvento.deleteById(idEvento);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Evento> listarId(int idEvento) {
		return dEvento.findById(idEvento);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Evento> buscarId(int idEvento) {
		return dEvento.findById(idEvento);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> listar() {
		return dEvento.findAll();

	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> buscarNombre(String nameEvento) {
		return dEvento.buscarNombre(nameEvento);

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Evento> buscarComple(int numcomplejidad) {
		return dEvento.buscarComple(numcomplejidad);

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Evento> buscarTevento(String nombreTipoEvento) {
		return dEvento.buscarTevento(nombreTipoEvento);

	}

	/*@Override
	@Transactional(readOnly = true)
	public List<Evento> buscarPriori(String numprioridad) {
		return dEvento.buscarPriori(numprioridad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> buscarImpor(String numimportancia) {
		return dEvento.buscarImpor(numimportancia);
	}
	*/
	
}
