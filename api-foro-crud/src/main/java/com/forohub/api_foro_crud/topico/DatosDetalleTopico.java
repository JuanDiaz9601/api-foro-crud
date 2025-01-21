package com.forohub.api_foro_crud.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(String mensaje, String nombreCurso, String titulo, Boolean status, LocalDateTime fecha) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getMensaje(), topico.getNombreCurso(), topico.getTitulo(), topico.getStatus(), topico.getFecha());
    }
}
