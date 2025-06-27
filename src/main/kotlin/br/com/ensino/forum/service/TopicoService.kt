package br.com.ensino.forum.service

import br.com.ensino.forum.dto.GetTopicoOutput
import br.com.ensino.forum.dto.PostTopicoDto
import br.com.ensino.forum.dto.PutTopicoDto
import br.com.ensino.forum.dto.TopicoPorCategoria
import br.com.ensino.forum.exception.NotFoundException
import br.com.ensino.forum.mapper.TopicoInputMapper
import br.com.ensino.forum.mapper.TopicoViewMapper
import br.com.ensino.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private val topicoRepository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoInputMapper: TopicoInputMapper,
) {

    fun listar(nomeCurso: String?, paginacao: Pageable): Page<GetTopicoOutput> {
        val topicos = if(nomeCurso == null) {
            topicoRepository.findAll(paginacao)
        } else {
            topicoRepository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map { it -> topicoViewMapper.map(it) }
    }

    fun listarPorId(id: Long): GetTopicoOutput {
        val topico = topicoRepository.findById(id).orElseThrow{
            NotFoundException("Not found here")
        }

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(topicoDto: PostTopicoDto): GetTopicoOutput {
        val topico = topicoInputMapper.map(topicoDto)
        topicoRepository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizarTopico(topicoDto: PutTopicoDto): GetTopicoOutput {
        val topico = topicoRepository.findById(topicoDto.id).orElseThrow{
            NotFoundException("Not found here")
        }
        topico.titulo = topicoDto.titulo
        topico.mensagem = topicoDto.mensagem
        topicoRepository.save(topico)
        return topicoViewMapper.map(topico)

    }

    fun deletarTopico(id: Long) {
        topicoRepository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoria> {
        return topicoRepository.relatorio()
    }
}