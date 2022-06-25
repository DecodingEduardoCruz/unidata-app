package br.dev.ec.unidata.domain.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dev.ec.unidata.domain.model.usuario.Role;

@Repository
public interface RoleRep extends JpaRepository<Role, Long>{
	Role findByName(String Name);

}
