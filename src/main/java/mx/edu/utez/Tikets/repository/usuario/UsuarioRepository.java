package mx.edu.utez.Tikets.repository.usuario;

import mx.edu.utez.Tikets.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
