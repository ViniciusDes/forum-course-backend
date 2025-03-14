package br.com.ensino.forum.controller

import br.com.ensino.forum.dto.GetTopicoOutput
import br.com.ensino.forum.dto.PostTopicoDto
import br.com.ensino.forum.dto.PutTopicoDto
import br.com.ensino.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/topicos")
class TopicoController (private val topicoService: TopicoService){
    @GetMapping
    fun listar():List<GetTopicoOutput>{
        return topicoService.listar()
    }

    @GetMapping("/{id}")
    fun listarPorId(@PathVariable id: Long): GetTopicoOutput {
        return topicoService.listarPorId(id)
    }

    @PostMapping
    fun criarTopico(
        @RequestBody @Valid topico: PostTopicoDto,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<GetTopicoOutput>{
        val topicoOutput = topicoService.cadastrar(topico)
        val url = uriBuilder.path("/topicos/${topicoOutput.id}").build().toUri()
        return ResponseEntity.created(url).body(topicoOutput)
    }

    @PutMapping
    fun atualizarTopico(@RequestBody @Valid topico: PutTopicoDto): ResponseEntity<GetTopicoOutput> {
        val topico = topicoService.atualizarTopico(topico)
        return ResponseEntity.ok(topico)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarTopico(@PathVariable id: Long){
        return topicoService.deletarTopico(id)
    }
}