package com.forohub.api_foro_crud.controller;



import com.forohub.api_foro_crud.topico.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico (@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        topicoRepository.save(new Topico(datosRegistroTopico));

    };

    @GetMapping
    public Page<DatosDetalleTopicoConId> listarTopicos (@PageableDefault(size = 1) Pageable paginacion){
        //Para crear una lista
        //return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
        //Para hacer una paginacion
        //Obtener todos los datos activos e inactivos
        return topicoRepository.findAll(paginacion).map(DatosDetalleTopicoConId::new);
        //return topicoRepository.findByStatusTrue(paginacion).map(DatosDetalleTopico::new);
    }

    @GetMapping("/{id}")
    public DatosDetalleTopico listarTopicoPorId(@PathVariable Long id) {
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            throw new RuntimeException("El tópico con id: " + id + " no existe!");
        }

        Topico topico = optionalTopico.get();

        if (!topico.getStatus()) {
            throw new RuntimeException("El tópico fue eliminado de la base de datos!");
        }

        return new DatosDetalleTopico(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public void actualizarTopico(@PathVariable Long id, @RequestBody DatosDetalleTopico datosDetalleTopico) {
        // Buscar el tópico por su ID
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            // Si no se encuentra, lanzar excepción
            throw new EntityNotFoundException("El tópico con id: " + id + " no se encuentra en la base de datos!");
        }
        // Obtener el tópico existente
        Topico topico = optionalTopico.get();
        // Actualizar el tópico con los nuevos datos
        topico.actualizarTopico(datosDetalleTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id){
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            // Si no se encuentra, lanzar excepción
            throw new EntityNotFoundException("El tópico con id: " + id + " no se encuentra en la base de datos!");
        }

        Topico topico = optionalTopico.get();

        topico.eliminarTopico();
    }

}
