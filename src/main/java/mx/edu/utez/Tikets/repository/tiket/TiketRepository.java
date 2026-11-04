package mx.edu.utez.Tikets.repository.tiket;

import mx.edu.utez.Tikets.model.tiket.Tiket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TiketRepository  extends JpaRepository<Tiket, Long> {
    List<Tiket> findByUsuarioId(Long usuarioId);

}
