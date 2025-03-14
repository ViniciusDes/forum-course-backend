package br.com.ensino.forum.service

import br.com.ensino.forum.dto.GetTopicoOutput
import br.com.ensino.forum.dto.PostTopicoDto
import br.com.ensino.forum.dto.PutTopicoDto
import br.com.ensino.forum.exception.NotFoundException
import br.com.ensino.forum.mapper.TopicoInputMapper
import br.com.ensino.forum.mapper.TopicoViewMapper
import br.com.ensino.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private var topicoViewMapper: TopicoViewMapper,
    private val topicoInputMapper: TopicoInputMapper
) {

    fun listar(): List<GetTopicoOutput> {
        return topicos.stream().map { it -> topicoViewMapper.map(it) }.collect(Collectors.toList())
    }

    fun listarPorId(id: Long): GetTopicoOutput {
        val topico = topicos.stream().filter({ t ->
            t.id == id
        }).findFirst().get()

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(topicoDto: PostTopicoDto): GetTopicoOutput {
        val topico = topicoInputMapper.map(topicoDto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(
            topico
        )

        return topicoViewMapper.map(topico)
    }

    fun atualizarTopico(topicoDto: PutTopicoDto): GetTopicoOutput {
        val topico = topicos.stream().filter { t ->
            t.id == topicoDto.id
        }.findFirst().orElseThrow{NotFoundException("Topico não encontrado")}
        val topicoAtualizado = Topico(
            id = topicoDto.id,
            titulo = topicoDto.titulo,
            mensagem = topicoDto.mensagem,
            dataCriacao = topico.dataCriacao,
            curso = topico.curso,
            autor = topico.autor,
            status = topico.status,
            respostas = topico.respostas
        )
        topicos = topicos.minus(topico).plus(
           topicoAtualizado
        )

        return topicoViewMapper.map(topicoAtualizado)

    }

    fun deletarTopico(id: Long) {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().orElseThrow{NotFoundException("Topico não encontrado")}

        topicos = topicos.minus(topico)
    }
}