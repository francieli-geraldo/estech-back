package br.com.scsoftware.afinese.infrastructure.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E> extends JpaRepository<E, Long> {
    Page<E> findAllByTenantIdAndActiveTrue(Pageable pageable, Long tenantId);

    Optional<E> findByIdAndTenantId(Long id, Long tenantId);
}
