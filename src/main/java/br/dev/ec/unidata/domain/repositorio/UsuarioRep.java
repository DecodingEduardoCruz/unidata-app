package br.dev.ec.unidata.domain.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.ec.unidata.domain.model.usuario.Usuario;

@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Long>{
	Usuario findByUsername(String username);
}
