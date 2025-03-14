package br.com.ensino.forum.mapper

import br.com.ensino.forum.dto.GetTopicoOutput
import br.com.ensino.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, GetTopicoOutput> {
    override fun map(input: Topico): GetTopicoOutput {
        return GetTopicoOutput(
                id = input.id,
                titulo = input.titulo,
                mensagem = input.mensagem,
                status = input.status,
                dataCriacao = input.dataCriacao,
        )
    }
}