package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Evento;
import pe.edu.upc.spring.model.Notificacion;
import pe.edu.upc.spring.repository.IEventoRepository;
import pe.edu.upc.spring.repository.INotificacionRepository;
import pe.edu.upc.spring.service.IEventoService;
import pe.edu.upc.spring.service.INotificacionService;
import pe.edu.upc.spring.service.ITipoEventoService;

@Service
public class NotificacionServiceImpl implements INotificacionService {

	@Autowired
	private INotificacionRepository dNotis;

	@Autowired
	private IEventoService eService;

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
	public List<Notificacion> buscarNombre(String nombreNotificacion, String username) {
		List<Evento> eventos= eService.buscarporUsername(username);
		List<Notificacion>noti1=dNotis.buscarNombre(nombreNotificacion);
		List<Notificacion>noti2=new ArrayList<Notificacion>();
		
		for(int i =0 ; i<noti1.size();i++) {
			for(int j =0 ; j< eventos.size(); j++) {
			if(noti1.get(i).getEvento() == eventos.get(j)){
				noti2.add(noti1.get(i));
			}
			}
		}
		return noti2;
		
	}
	
	@Override
	@Transactional
	public List<Notificacion> buscarDescripcion(String descripcionNotificacion,String username) {
		List<Evento> eventos= eService.buscarporUsername(username);
		List<Notificacion>noti1=dNotis.buscarDescripcion(descripcionNotificacion);
		List<Notificacion>noti2=new ArrayList<Notificacion>();
		
		for(int i =0 ; i<noti1.size();i++) {
			for(int j =0 ; j< eventos.size(); j++) {
			if(noti1.get(i).getEvento() == eventos.get(j)){
				noti2.add(noti1.get(i));
			}
			}
		}
		return noti2;
	}
	
	@Override
	@Transactional
	public List<Notificacion> buscarNevento(String nombreEvento,String username) {
		List<Evento> eventos= eService.buscarporUsername(username);
		List<Notificacion>noti1=dNotis.buscarNevento(nombreEvento);
		List<Notificacion>noti2=new ArrayList<Notificacion>();
		
		for(int i =0 ; i<noti1.size();i++) {
			for(int j =0 ; j< eventos.size(); j++) {
			if(noti1.get(i).getEvento() == eventos.get(j)){
				noti2.add(noti1.get(i));
			}
			}
		}
		return noti2;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Notificacion> buscarporUsername(String username) {
		List<Evento> eventos= eService.buscarporUsername(username);
		List<Notificacion> NotisO= dNotis.findAll();
		List<Notificacion> Notis=new ArrayList<Notificacion>();

		for(int i =0 ; i<NotisO.size();i++) {
			for(int j =0 ; j< eventos.size(); j++) {
			if(NotisO.get(i).getEvento() == eventos.get(j)){
				Notis.add(NotisO.get(i));
			}
			}
		}
		return Notis;
	}
}
