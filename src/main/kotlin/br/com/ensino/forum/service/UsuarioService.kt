package br.com.ensino.forum.service

import br.com.ensino.forum.model.Usuario
import br.com.ensino.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(var usuarioRp: UsuarioRepository) {
    fun encontrarUsuarioPorId(id: Long): Usuario{
      return usuarioRp.findById(id).orElseThrow {
          NoSuchElementException("Usuário com ID $id não encontrado")
      }
    }
}
