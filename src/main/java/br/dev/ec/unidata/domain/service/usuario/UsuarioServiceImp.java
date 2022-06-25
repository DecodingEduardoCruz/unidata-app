package br.dev.ec.unidata.domain.service.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.dev.ec.unidata.UnidataApplication;
import br.dev.ec.unidata.domain.model.usuario.Role;
import br.dev.ec.unidata.domain.model.usuario.Usuario;
import br.dev.ec.unidata.domain.repositorio.RoleRep;
import br.dev.ec.unidata.domain.repositorio.UsuarioRep;
import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional
public class UsuarioServiceImp implements UsuarioService, UserDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(UnidataApplication.class);
	private final PasswordEncoder passwordEncoder;
	private final UsuarioRep usuarioRep;
	private final RoleRep roleRep;
	
	@Override
	public List<Usuario> getUsuarios() {
		List<Usuario> usuario = usuarioRep.findAll();
		if(usuario.isEmpty()) throw new NullPointerException("Não encontramos usuários cadastrados!");
		
		logger.info("Encontramos {} cadastros", usuario.size());
		return usuario;
	}
	@Override
	public Usuario username(String username) {
		Usuario usuario = usuarioRep.findByUsername(username);
		if(usuario == null) throw new NullPointerException("Usuário não encontrado!");
	    	    
		logger.info("Usuario encontrado {}", usuario.getName());
		return usuario;
	}
	@Override
	public Usuario salvar(Usuario usuario) {
		if(usuario == null) throw new NullPointerException("Todos os campos são obrigatórios!");
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		logger.info("Usuário cadastrado {}!", usuario.getName());
		return usuarioRep.save(usuario);
	}
	
	@Override
	public Role saveRole(Role role) {
		if(role == null) throw new NullPointerException("Nome para permissão é obrigatória!");
		
		logger.info("Nova permissão cadastrada {}", role.getName());
		return roleRep.save(role);
	}
	
	@Override
	public void addRole(String username, String roleName) {	
		if(username == null || roleName == null) throw new NullPointerException("Usuário e Permissão são obrigatórios!");
		
		Role role = roleRep.findByName(roleName);
		Usuario usuario = usuarioRep.findByUsername(username);	
		
		logger.info("Usuário {} recebeu a permissão {}", usuario.getName(), role.getName());
		usuario.getRoles().add(role);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Usuario usuario = usuarioRep.findByUsername(username);
		if(usuario == null) {
			logger.info("Usuario não encontrado!");
			throw new UsernameNotFoundException("Usuário não encontrado!");			
		}
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		usuario.getRoles().forEach(role -> { 
			authorities.add(new SimpleGrantedAuthority(role.getName())); 
		});
		
		logger.info("Autenticando usuário {}!", usuario.getName());
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), authorities);
	}

}
