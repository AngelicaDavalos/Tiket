package mx.edu.utez.Tikets.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.Tikets.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.Tikets.controller.usuario.dto.ResponseUserDto;
import mx.edu.utez.Tikets.service.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ResponseUserDto> crearUsuario(@Valid @RequestBody RequestCreateUserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(dto));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ResponseUserDto> desactivarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.desactivarUsuario(id));
    }
}
