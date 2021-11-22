package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Prueba;
import pe.edu.upc.spring.model.TipoEvento;
import pe.edu.upc.spring.repository.IPruebaRepository;
import pe.edu.upc.spring.service.IPruebaService;
import pe.edu.upc.spring.service.ITipoEventoService;

@Service
public class PruebaServiceImpl implements IPruebaService {

	@Autowired
	private IPruebaRepository dPrueba;
	
	@Autowired
	private ITipoEventoService tService;

	
	@Override
	@Transactional
	public boolean registrar(Prueba prueba) {
		Prueba objPrueba = dPrueba.save(prueba);
		if (objPrueba == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPrueba) {
		dPrueba.deleteById(idPrueba);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Prueba> listarId(int idPrueba) {
		return dPrueba.findById(idPrueba);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Prueba> buscarId(int idPrueba) {
		return dPrueba.findById(idPrueba);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prueba> listar() {
		return dPrueba.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Prueba> buscarNombre(String namePrub, String username) {
		List<TipoEvento> listaTipoEventos = tService.buscarporUsername(username);
		List<Prueba> PruebasO= dPrueba.buscarNombre(namePrub);
		List<Prueba> Pruebas=new ArrayList<Prueba>();
		
		for(int i =0 ; i<PruebasO.size();i++) {
			for(int j =0 ; j< listaTipoEventos.size(); j++) {
			if(PruebasO.get(i).getTevento() == listaTipoEventos.get(j)){
				Pruebas.add(PruebasO.get(i));
			}
			}
		}
		return Pruebas;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Prueba> buscarTevento(String nombreTevento,String username) {
		List<TipoEvento> listaTipoEventos = tService.buscarporUsername(username);
		List<Prueba> PruebasO= dPrueba.buscarTevento(nombreTevento);
		List<Prueba> Pruebas=new ArrayList<Prueba>();
		
		for(int i =0 ; i<PruebasO.size();i++) {
			for(int j =0 ; j< listaTipoEventos.size(); j++) {
			if(PruebasO.get(i).getTevento() == listaTipoEventos.get(j)){
				Pruebas.add(PruebasO.get(i));
			}
			}
		}
		return Pruebas;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Prueba> buscarporUsername(String username) {
		List<TipoEvento> listaTipoEventos = tService.buscarporUsername(username);
		List<Prueba> PruebasO= dPrueba.findAll();
		List<Prueba> Pruebas=new ArrayList<Prueba>();

		for(int i =0 ; i<PruebasO.size();i++) {
			for(int j =0 ; j< listaTipoEventos.size(); j++) {
			if(PruebasO.get(i).getTevento() == listaTipoEventos.get(j)){
				Pruebas.add(PruebasO.get(i));
			}
			}
		}
		return Pruebas;

	}
}
