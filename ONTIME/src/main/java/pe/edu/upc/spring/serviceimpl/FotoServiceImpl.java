package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Foto;
import pe.edu.upc.spring.model.TipoEvento;
import pe.edu.upc.spring.repository.IFotoRepository;
import pe.edu.upc.spring.repository.ITipoEventoRepository;
import pe.edu.upc.spring.service.IFotoService;
import pe.edu.upc.spring.service.ITipoEventoService;

@Service
public class FotoServiceImpl implements IFotoService {

	@Autowired
	private IFotoRepository dFoto;
	
	@Autowired
	private ITipoEventoRepository tService;

	
	@Override
	@Transactional
	public boolean registrar(Foto foto) {
		Foto objFoto = dFoto.save(foto);
		if (objFoto == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idFoto) {
		dFoto.deleteById(idFoto);
	}

	@Override
	@Transactional(readOnly = true)
	public Foto listarId(int idFoto) {
		Optional<Foto> ft=dFoto.findById(idFoto);
		return ft.isPresent() ? ft.get() : new Foto();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Foto> buscarId(int idFoto) {
		return dFoto.findById(idFoto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Foto> listar() {
		return dFoto.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Foto> buscarNombre(String namePho, String username) {
		List<TipoEvento> listaTipoEventos = tService.findByPersonaUsername(username);
		List<Foto> FotosO= dFoto.buscarNombre(namePho);
		List<Foto> Fotos=new ArrayList<Foto>();
		
		for(int i =0 ; i<FotosO.size();i++) {
			for(int j =0 ; j< listaTipoEventos.size(); j++) {
			if(FotosO.get(i).getTevento() == listaTipoEventos.get(j)){
				Fotos.add(FotosO.get(i));
				
				
			}
			}
		}
		return Fotos;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Foto> buscarTevento(String nombreTevento, String username) {
		List<TipoEvento> listaTipoEventos = tService.findByPersonaUsername(username);
		List<Foto> FotosO= dFoto.buscarTevento(nombreTevento);
		List<Foto> Fotos=new ArrayList<Foto>();
		
		for(int i =0 ; i<FotosO.size();i++) {
			for(int j =0 ; j< listaTipoEventos.size(); j++) {
			if(FotosO.get(i).getTevento() == listaTipoEventos.get(j)){
				Fotos.add(FotosO.get(i));
				
				
			}
			}
		}
		return Fotos;
	}
	@Override
	@Transactional(readOnly = true)
	public List<Foto> buscarporUsername(String username) {
		List<TipoEvento> listaTipoEventos = tService.findByPersonaUsername(username);
		List<Foto> FotosO= dFoto.findAll();
		List<Foto> Fotos=new ArrayList<Foto>();

		for(int i =0 ; i<FotosO.size();i++) {
			for(int j =0 ; j< listaTipoEventos.size(); j++) {
			if(FotosO.get(i).getTevento() == listaTipoEventos.get(j)){
				Fotos.add(FotosO.get(i));
			}
			}
		}
		return Fotos;

	}
}
