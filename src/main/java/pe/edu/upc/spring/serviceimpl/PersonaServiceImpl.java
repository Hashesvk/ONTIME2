package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Role;
import pe.edu.upc.spring.repository.IPersonaRepository;
import pe.edu.upc.spring.repository.IRoleRepository;
import pe.edu.upc.spring.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaRepository dPersona;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Override
	@Transactional
	public boolean registrar(Persona persona) {
		Persona objPersona = dPersona.save(persona);
		if (objPersona == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPersona) {
		dPersona.deleteById(idPersona);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> listarId(int idPersona) {
		return dPersona.findById(idPersona);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Persona> buscarId(int idPersona) {
		return dPersona.findById(idPersona);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> listar() {
		return dPersona.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> listarRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<Persona> listarporUsername(String username) {
		Persona persona= dPersona.findByUsername(username);
		List<Persona> personas=new ArrayList<Persona>();
		personas.add(persona);
		return personas;
	}
	
	
}
