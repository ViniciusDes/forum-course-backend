package br.com.ensino.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class PostTopicoDto(
    @field:NotEmpty @field:Size(min = 5, max = 12)
    val titulo: String,
    @field:NotEmpty val mensagem: String,
    val idCurso: Long,
    val idAutor: Long
) {
}