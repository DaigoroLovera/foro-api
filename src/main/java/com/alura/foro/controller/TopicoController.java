package com.alura.foro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import com.alura.foro.model.Topico;
import com.alura.foro.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // === Crear un nuevo tópico ===
    @PostMapping
    public ResponseEntity<?> crearTopico(@RequestBody @Valid Topico topico) {
        // Validar duplicados
        if (topicoRepository.existsByTituloAndMensaje(topico.getTitulo(), topico.getMensaje())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Ya existe un tópico con el mismo título y mensaje");
        }

        // Guardar el tópico en la base de datos
        Topico nuevoTopico = topicoRepository.save(topico);
        return ResponseEntity.ok(nuevoTopico);
    }

    // === Listar todos los tópicos ===
    @GetMapping
    public ResponseEntity<?> listarTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }

    // === Detalle de un tópico por ID ===
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopico(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> ResponseEntity.ok(topico))
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body("Error: No se encontró un tópico con ID " + id));
    }

    // === Actualizar un tópico por ID ===
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody @Valid Topico topicoActualizado) {

        // Buscar el tópico en la base de datos
        return topicoRepository.findById(id).map(topico -> {

            // Validar duplicados (exceptuando el mismo ID)
            if (topicoRepository.existsByTituloAndMensaje(topicoActualizado.getTitulo(), topicoActualizado.getMensaje())
                    && !(topico.getTitulo().equals(topicoActualizado.getTitulo())
                    && topico.getMensaje().equals(topicoActualizado.getMensaje()))) {
                return ResponseEntity
                        .badRequest()
                        .body("Error: Ya existe un tópico con el mismo título y mensaje");
            }

            // Actualizar los campos
            topico.setTitulo(topicoActualizado.getTitulo());
            topico.setMensaje(topicoActualizado.getMensaje());
            topico.setAutor(topicoActualizado.getAutor());
            topico.setCurso(topicoActualizado.getCurso());
            topico.setEstado(topicoActualizado.getEstado());

            Topico guardado = topicoRepository.save(topico);
            return ResponseEntity.ok(guardado);

        }).orElseGet(() -> ResponseEntity
                .status(404)
                .body("Error: No se encontró un tópico con ID " + id));
    }
}