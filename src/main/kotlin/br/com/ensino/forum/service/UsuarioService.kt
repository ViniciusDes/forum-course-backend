package br.com.ensino.forum.service

import br.com.ensino.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(var usuarios: List<Usuario>) {
    init {
        val usuario = Usuario(
            id = 1,
            nome = "Vini",
            email = "vini@mail"
        )
        usuarios = Arrays.asList(usuario)
    }

    fun encontrarUsuarioPorId(id: Long): Usuario{
        val usuario = usuarios.stream().filter { it -> it.id == id }.findFirst().get()
        return  usuario
    }
}
