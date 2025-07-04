package br.com.ensino.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class PutTopicoDto(
    @field:NotNull
    val id: Long,
    @field:NotEmpty @field:Size(min = 5, max = 12)
    val titulo: String,
    @field:NotEmpty
    val mensagem: String,
)