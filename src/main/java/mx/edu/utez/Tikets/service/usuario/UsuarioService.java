package mx.edu.utez.Tikets.service.usuario;

import mx.edu.utez.Tikets.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.Tikets.controller.usuario.dto.ResponseUserDto;
import mx.edu.utez.Tikets.exception.customExceptions.CustomNotFoundException;
import mx.edu.utez.Tikets.model.usuario.Usuario;
import mx.edu.utez.Tikets.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseUserDto crearUsuario(RequestCreateUserDto dto) {
        Usuario usuario = new Usuario(dto.getNombre(), true);
        Usuario guardado = usuarioRepository.save(usuario);
        return new ResponseUserDto(guardado.getId(), guardado.getNombre(), guardado.isActivo());
    }

    @Transactional
    public ResponseUserDto desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Usuario con ID " + id + " no encontrado."));

        usuario.setActivo(false);
        Usuario guardado = usuarioRepository.save(usuario);
        return new ResponseUserDto(guardado.getId(), guardado.getNombre(), guardado.isActivo());
    }
}
