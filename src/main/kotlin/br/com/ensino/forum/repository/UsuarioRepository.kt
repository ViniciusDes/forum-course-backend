package br.com.ensino.forum.repository

import br.com.ensino.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}