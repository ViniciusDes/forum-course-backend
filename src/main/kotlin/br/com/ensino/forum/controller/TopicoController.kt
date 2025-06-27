package br.com.ensino.forum.controller

import br.com.ensino.forum.dto.GetTopicoOutput
import br.com.ensino.forum.dto.PostTopicoDto
import br.com.ensino.forum.dto.PutTopicoDto
import br.com.ensino.forum.dto.TopicoPorCategoria
import br.com.ensino.forum.service.CursoService
import br.com.ensino.forum.service.TopicoService
import br.com.ensino.forum.service.UsuarioService
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController (
    private val topicoService: TopicoService,
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
){
    @GetMapping
    @Cacheable("topicos")
    fun listar(@RequestParam(required = false) nomeCurso: String?,
               @PageableDefault(size = 5) paginacao: Pageable
    ):Page<GetTopicoOutput> {
        return topicoService.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    fun listarPorId(@PathVariable id: Long): GetTopicoOutput {
        return topicoService.listarPorId(id)
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun criarTopico(
        @RequestBody @Valid topico: PostTopicoDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<GetTopicoOutput>{
        val topicoOutput = topicoService.cadastrar(topico)
        print(topicoOutput)
        val url = uriBuilder.path("/topicos/${topicoOutput.id}").build().toUri()
        return ResponseEntity.created(url).body(topicoOutput)
    }

    @Transactional
    @PutMapping
    fun atualizarTopico(@RequestBody @Valid topico: PutTopicoDto): ResponseEntity<GetTopicoOutput> {
        val topico = topicoService.atualizarTopico(topico)
        return ResponseEntity.ok(topico)
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarTopico(@PathVariable id: Long){
        return topicoService.deletarTopico(id)
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoria> {
        return topicoService.relatorio()
    }
}