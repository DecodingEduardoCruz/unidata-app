package br.dev.ec.unidata.resources.domain.repositorio;

import br.dev.ec.unidata.resources.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long>{
	Usuario findByUsername(String username);
}
