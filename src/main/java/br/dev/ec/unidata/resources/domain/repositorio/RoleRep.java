package br.dev.ec.unidata.resources.domain.repositorio;

import br.dev.ec.unidata.resources.domain.usuario.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRep extends JpaRepository<Role, Long>{
	Role findByName(String Name);

}
