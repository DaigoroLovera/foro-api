package com.alura.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.foro.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Método para validar que no existan tópicos duplicados
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}