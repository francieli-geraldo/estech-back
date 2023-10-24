package br.com.scsoftware.estech.domains.auth.repository;

import br.com.scsoftware.estech.domains.auth.entity.UserHistory;
import br.com.scsoftware.estech.infrastructure.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samuel-cruz
 */
@Repository
public interface UserHistoryRepository extends BaseRepository<UserHistory> {

}
