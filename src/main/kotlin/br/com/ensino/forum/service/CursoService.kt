package br.com.ensino.forum.service

import br.com.ensino.forum.model.Curso
import br.com.ensino.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val cursoRp: CursoRepository) {
    fun encontrarCursoPorId(id: Long): Curso {
        return cursoRp.findById(id).orElseThrow {
            NoSuchElementException("Curso com ID $id não encontrado")
        }
    }
}
