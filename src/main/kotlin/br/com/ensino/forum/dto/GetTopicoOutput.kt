package br.com.ensino.forum.dto

import br.com.ensino.forum.model.StatusTopico
import java.time.LocalDateTime

data class GetTopicoOutput(
    val id: Long?,
    var titulo: String,
    var mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)
