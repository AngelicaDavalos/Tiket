package mx.edu.utez.Tikets.controller.tiket;

import jakarta.validation.Valid;
import mx.edu.utez.Tikets.controller.tiket.dto.RequestChangePriorityDto;
import mx.edu.utez.Tikets.controller.tiket.dto.RequestCreateTiketDTO;
import mx.edu.utez.Tikets.controller.tiket.dto.ResponseTicketDto;
import mx.edu.utez.Tikets.service.tiket.TiketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tiket")
@CrossOrigin(origins = "*")
public class TiketController {
    private final TiketService ticketService;

    public TiketController(TiketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<ResponseTicketDto> crearTicket(@Valid @RequestBody RequestCreateTiketDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.crearTicket(dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ResponseTicketDto> actualizarEstadoTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.actualizarEstadoTicket(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<ResponseTicketDto> reasignarPrioridad(
            @PathVariable Long id,
            @Valid @RequestBody RequestChangePriorityDto dto) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(id, dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ResponseTicketDto>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}
