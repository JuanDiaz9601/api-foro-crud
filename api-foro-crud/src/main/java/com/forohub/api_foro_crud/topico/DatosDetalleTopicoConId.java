package com.forohub.api_foro_crud.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopicoConId(String mensaje, String nombreCurso, String titulo, Boolean status, LocalDateTime fecha, Long id) {
    public DatosDetalleTopicoConId(Topico topico) {
        this(topico.getMensaje(), topico.getNombreCurso(), topico.getTitulo(), topico.getStatus(), topico.getFecha(), topico.getId());
    }
}
