package com.alura.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.alura.foro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}