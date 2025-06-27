package br.com.ensino.forum.repository

import br.com.ensino.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}