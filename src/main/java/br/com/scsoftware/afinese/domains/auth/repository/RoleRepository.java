package br.com.scsoftware.afinese.domains.auth.repository;

import br.com.scsoftware.afinese.domains.auth.entity.Role;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samuel-cruz
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {

}
