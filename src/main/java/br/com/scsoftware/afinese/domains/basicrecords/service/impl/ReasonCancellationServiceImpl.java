package br.com.scsoftware.afinese.domains.basicrecords.service.impl;

import br.com.scsoftware.afinese.domains.basicrecords.entity.ReasonCancellation;
import br.com.scsoftware.afinese.domains.basicrecords.repository.ReasonCancellationRepository;
import br.com.scsoftware.afinese.domains.basicrecords.service.ReasonCancellationService;
import org.springframework.stereotype.Service;

@Service
public class ReasonCancellationServiceImpl extends BaseServiceImpl<ReasonCancellation> implements ReasonCancellationService {

    public ReasonCancellationServiceImpl(final ReasonCancellationRepository repository) {
        super(repository);
    }
}
