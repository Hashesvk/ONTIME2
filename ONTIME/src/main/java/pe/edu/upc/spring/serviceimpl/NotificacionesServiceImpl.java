package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Notificaciones;
import pe.edu.upc.spring.repository.INotificacionesRepository;
import pe.edu.upc.spring.service.INotificacionesService;

@Service
public class NotificacionesServiceImpl implements INotificacionesService {

	@Autowired
	private INotificacionesRepository dNotis;

	@Override
	@Transactional
	public boolean registrar(Notificaciones Notificaciones) {
		Notificaciones objNotificaciones = dNotis.save(Notificaciones);
		if (objNotificaciones == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idNotificaciones) {
		dNotis.deleteById(idNotificaciones);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Notificaciones> listarId(int idNotificaciones) {
		return dNotis.findById(idNotificaciones);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Notificaciones> buscarId(int idNotificaciones) {
		return dNotis.findById(idNotificaciones);

	}

	@Override
	@Transactional
	public List<Notificaciones> listar() {
		return dNotis.findAll();
	}

	@Override
	@Transactional
	public List<Notificaciones> buscarNombre(String nombreNotificaciones) {
		// TODO Auto-generated method stub
		return dNotis.buscarNombre(nombreNotificaciones);
	}
	
	
	
	
	
}
