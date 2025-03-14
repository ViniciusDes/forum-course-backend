package br.com.ensino.forum.service

import br.com.ensino.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(var cursos: List<Curso>) {
    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programação"
        )
        cursos = Arrays.asList(curso)
    }

    fun encontrarCursoPorId(id: Long): Curso{
        val curso = cursos.stream().filter { it -> it.id == id }.findFirst().get()
        return  curso
    }
}
