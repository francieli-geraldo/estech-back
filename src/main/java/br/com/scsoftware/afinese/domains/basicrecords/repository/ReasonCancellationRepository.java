package br.com.scsoftware.afinese.domains.basicrecords.repository;

import br.com.scsoftware.afinese.domains.basicrecords.entity.ReasonCancellation;
import br.com.scsoftware.afinese.infrastructure.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReasonCancellationRepository extends BaseRepository<ReasonCancellation> {
    
}
