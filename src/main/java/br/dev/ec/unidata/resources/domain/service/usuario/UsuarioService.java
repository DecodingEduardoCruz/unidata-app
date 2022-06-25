package br.dev.ec.unidata.resources.domain.service.usuario;

import java.util.List;

import br.dev.ec.unidata.resources.domain.usuario.Role;
import br.dev.ec.unidata.resources.domain.usuario.Usuario;

public interface UsuarioService {
	List<Usuario> getUsuarios();
	Usuario username(String username);
	Usuario salvar(Usuario usuario);
	
	Role saveRole(Role role);	
	void addRole(String username, String roleName);

}
