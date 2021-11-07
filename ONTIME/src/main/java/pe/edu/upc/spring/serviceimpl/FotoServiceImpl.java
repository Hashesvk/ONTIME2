package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Foto;
import pe.edu.upc.spring.repository.IFotoRepository;
import pe.edu.upc.spring.service.IFotoService;

@Service
public class FotoServiceImpl implements IFotoService {

	@Autowired
	private IFotoRepository dFoto;
	
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
	public List<Foto> buscarNombre(String namePho) {
		return dFoto.buscarNombre(namePho);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Foto> buscarTevento(String nombreTevento) {
		return dFoto.buscarTevento(nombreTevento);
	}

}
