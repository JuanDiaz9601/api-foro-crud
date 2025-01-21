package com.forohub.api_foro_crud.topico;

import com.forohub.api_foro_crud.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
//Equals indica el valor unico por el cual se identifica los objetos creados
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    //@Column(name = "nombre_curso")
    private String nombreCurso;
    private Boolean status;
    private LocalDateTime fecha;
    //private Usuario usuario;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.mensaje = datosRegistroTopico.mensaje();
        this.titulo = datosRegistroTopico.titulo();
        this.nombreCurso = datosRegistroTopico.nombreCurso();
        this.status = true;
        this.fecha = datosRegistroTopico.fecha();
    }

    public void actualizarTopico(DatosDetalleTopico datosDetalleTopico) {
        if(datosDetalleTopico.mensaje() != null){
            this.mensaje = datosDetalleTopico.mensaje();
        }
        if(datosDetalleTopico.titulo() != null){
            this.titulo = datosDetalleTopico.titulo();
        }
        if(datosDetalleTopico.nombreCurso() != null){
            this.nombreCurso = datosDetalleTopico.nombreCurso();
        }

    }

    public void eliminarTopico() {
        this.status = false;
    }
}
