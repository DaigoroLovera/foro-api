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

    // === NUEVO: Listar todos los tópicos ===
    @GetMapping
    public ResponseEntity<?> listarTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }
}