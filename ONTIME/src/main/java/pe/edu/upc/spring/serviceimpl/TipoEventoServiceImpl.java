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
import pe.edu.upc.spring.service.ITipoEventoService;

@Service
public class TipoEventoServiceImpl implements ITipoEventoService {
	
	@Autowired
	private ITipoEventoRepository dtipoevento;
	@Autowired
	private IEventoRepository dEvento;
	
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
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoEvento> buscarporUsername(String username){
		List<Evento> eventos= dEvento.findByPersonaUsername(username);
		List<TipoEvento> teventos=new ArrayList<TipoEvento>();
		for(int i =0 ; i<eventos.size();i++) {
			if(!teventos.contains(eventos.get(i).getTipoEvento())){
				teventos.add(eventos.get(i).getTipoEvento());
				System.out.println(eventos.get(i).getTipoEvento().getDescripcionTipoEvento());
			}
		}
		return teventos;
	}

}
