package br.com.scsoftware.estech.domains.basicrecords.repository;

import br.com.scsoftware.estech.domains.basicrecords.entity.ReasonCancellation;
import br.com.scsoftware.estech.infrastructure.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonCancellationRepository extends BaseRepository<ReasonCancellation> {
    
}
