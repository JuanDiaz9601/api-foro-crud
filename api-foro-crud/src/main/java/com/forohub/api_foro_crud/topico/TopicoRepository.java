package com.forohub.api_foro_crud.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    //El tipo de dato debe ser el mismo que el del controller Page para este caso
    Page<Topico> findByStatusTrue(Pageable paginacion);
}
