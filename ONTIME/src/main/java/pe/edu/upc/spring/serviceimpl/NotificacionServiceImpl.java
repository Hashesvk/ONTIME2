package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Notificacion;
import pe.edu.upc.spring.repository.INotificacionRepository;
import pe.edu.upc.spring.service.INotificacionService;

@Service
public class NotificacionServiceImpl implements INotificacionService {

	@Autowired
	private INotificacionRepository dNotis;

	@Override
	@Transactional
	public boolean registrar(Notificacion notificacion) {
		Notificacion objNotificacion = dNotis.save(notificacion);
		if (objNotificacion == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idNotificacion) {
		dNotis.deleteById(idNotificacion);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Notificacion> listarId(int idNotificacion) {
		return dNotis.findById(idNotificacion);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Notificacion> buscarId(int idNotificacion) {
		return dNotis.findById(idNotificacion);

	}

	@Override
	@Transactional
	public List<Notificacion> listar() {
		return dNotis.findAll();
	}

	@Override
	@Transactional
	public List<Notificacion> buscarNombre(String nombreNotificacion) {
		return dNotis.buscarNombre(nombreNotificacion);
	}
	
	@Override
	@Transactional
	public List<Notificacion> buscarDescripcion(String descripcionNotificacion) {
		return dNotis.buscarDescripcion(descripcionNotificacion);
	}
	
	@Override
	@Transactional
	public List<Notificacion> buscarNevento(String nombreEvento) {
		return dNotis.buscarNevento(nombreEvento);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Notificacion> buscarporUsername(String username) {
		return dNotis.findByPersonaUsername(username);

	}
}
