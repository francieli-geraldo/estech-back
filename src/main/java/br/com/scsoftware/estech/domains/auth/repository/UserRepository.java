package br.com.scsoftware.estech.domains.auth.repository;

import br.com.scsoftware.estech.domains.auth.entity.User;
import br.com.scsoftware.estech.infrastructure.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samuel-cruz
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

}
