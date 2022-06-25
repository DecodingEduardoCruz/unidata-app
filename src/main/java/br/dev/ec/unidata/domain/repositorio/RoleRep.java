package br.dev.ec.unidata.domain.repositorio;

import br.dev.ec.unidata.domain.usuario.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRep extends JpaRepository<Role, Long>{
	Role findByName(String Name);

}
