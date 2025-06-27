package br.com.ensino.forum.repository

import br.com.ensino.forum.dto.TopicoPorCategoria
import br.com.ensino.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository: JpaRepository<Topico, Long> {
    fun findByCursoNome(nomeCurso: String, pageable: Pageable): Page<Topico>
    @Query("SELECT new br.com.ensino.forum.dto.TopicoPorCategoria(curso.categoria, count(t)) FROM Topico t join t.curso curso GROUP BY curso.categoria")
    fun relatorio(): List<TopicoPorCategoria>
}