package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Evento;
import pe.edu.upc.spring.model.TipoEvento;
import pe.edu.upc.spring.repository.IEventoRepository;
import pe.edu.upc.spring.repository.ITipoEventoRepository;
import pe.edu.upc.spring.service.IEventoService;

@Service
public class EventoServiceImpl implements IEventoService{
	
	@Autowired
	private IEventoRepository dEvento;

	@Autowired
	private ITipoEventoRepository tService;
	
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
	public List<Evento> buscarTevento(String nombreTEvento) {
		return dEvento.buscarTevento(nombreTEvento);

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Evento> buscarporUsername(String username) {
		List<TipoEvento> listaTipoEventos = tService.findByPersonaUsername(username);
		List<Evento> EventosO= dEvento.findAll();
		List<Evento> Eventos=new ArrayList<Evento>();

		for(int i =0 ; i<EventosO.size();i++) {
			for(int j =0 ; j< listaTipoEventos.size(); j++) {
			if(EventosO.get(i).getTipoEvento() == listaTipoEventos.get(j)){
				Eventos.add(EventosO.get(i));
			}
			}
		}
		return Eventos;
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
