package br.com.ensino.forum.mapper

import br.com.ensino.forum.dto.PostTopicoDto
import br.com.ensino.forum.model.Topico
import br.com.ensino.forum.service.CursoService
import br.com.ensino.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoInputMapper(
    val cursoService: CursoService,
    val usuarioService: UsuarioService,
): Mapper<PostTopicoDto, Topico> {
    override fun map(input: PostTopicoDto): Topico {
       return Topico(
            mensagem = input.mensagem,
            titulo = input.titulo,
            curso = cursoService.encontrarCursoPorId(input.idCurso),
            autor = usuarioService.encontrarUsuarioPorId(input.idAutor),
        )
    }

}
