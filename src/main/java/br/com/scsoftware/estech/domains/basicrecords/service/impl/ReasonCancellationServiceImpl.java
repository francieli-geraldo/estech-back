package br.com.scsoftware.estech.domains.basicrecords.service.impl;

import br.com.scsoftware.estech.domains.basicrecords.entity.ReasonCancellation;
import br.com.scsoftware.estech.domains.basicrecords.repository.ReasonCancellationRepository;
import br.com.scsoftware.estech.domains.basicrecords.service.ReasonCancellationService;
import org.springframework.stereotype.Service;

@Service
public class ReasonCancellationServiceImpl extends BaseServiceImpl<ReasonCancellation> implements ReasonCancellationService {

    public ReasonCancellationServiceImpl(final ReasonCancellationRepository repository) {
        super(repository);
    }
}
