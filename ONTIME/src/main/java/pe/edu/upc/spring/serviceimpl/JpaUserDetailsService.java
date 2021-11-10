package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Persona;
import pe.edu.upc.spring.model.Role;
import pe.edu.upc.spring.repository.IPersonaRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IPersonaRepository personaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		Persona persona = personaRepository.findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role : persona.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthoriry()));
		}
		return new User(persona.getUsername(), persona.getPassword(), true, true, true, true, authorities);
	}

}