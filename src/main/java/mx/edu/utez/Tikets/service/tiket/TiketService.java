package mx.edu.utez.Tikets.service.tiket;

import mx.edu.utez.Tikets.controller.tiket.dto.RequestChangePriorityDto;
import mx.edu.utez.Tikets.controller.tiket.dto.RequestCreateTiketDTO;
import mx.edu.utez.Tikets.controller.tiket.dto.ResponseTicketDto;
import mx.edu.utez.Tikets.exception.customExceptions.CustomBadException;
import mx.edu.utez.Tikets.exception.customExceptions.CustomNotFoundException;
import mx.edu.utez.Tikets.model.tiket.EstadoTiket;
import mx.edu.utez.Tikets.model.tiket.Tiket;
import mx.edu.utez.Tikets.model.usuario.Usuario;
import mx.edu.utez.Tikets.repository.tiket.TiketRepository;
import mx.edu.utez.Tikets.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TiketService {

    private final TiketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public TiketService(TiketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Transactional
    public ResponseTicketDto crearTicket(RequestCreateTiketDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new CustomNotFoundException("Usuario con ID " + dto.getUsuarioId() + " no encontrado."));

        if (!usuario.isActivo()) {
            throw new CustomBadException("No se puede crear un ticket para un usuario inactivo.");
        }

        Tiket ticket = new Tiket(
                dto.getDescripcion(),
                EstadoTiket.ABIERTO,
                dto.getPrioridad(),
                usuario
        );

        Tiket guardado = ticketRepository.save(ticket);
        return mapToDto(guardado);
    }

    @Transactional
    public ResponseTicketDto actualizarEstadoTicket(Long id) {
        Tiket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Ticket con ID " + id + " no encontrado."));

        if (ticket.getEstado() == EstadoTiket.CERRADO) {
            throw new CustomBadException("El ticket ya está CERRADO y no se puede modificar.");
        }

        if (ticket.getEstado() == EstadoTiket.ABIERTO) {
            ticket.setEstado(EstadoTiket.EN_PROCESO);
        } else if (ticket.getEstado() == EstadoTiket.EN_PROCESO) {
            ticket.setEstado(EstadoTiket.CERRADO);
        }

        Tiket actualizado = ticketRepository.save(ticket);
        return mapToDto(actualizado);
    }

    @Transactional
    public ResponseTicketDto reasignarPrioridad(Long id, RequestChangePriorityDto dto) {
        Tiket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Ticket con ID " + id + " no encontrado."));

        if (ticket.getEstado() == EstadoTiket.CERRADO) {
            throw new CustomBadException("No se puede reasignar la prioridad a un ticket CERRADO.");
        }

        ticket.setPrioridad(dto.getPrioridad());
        Tiket actualizado = ticketRepository.save(ticket);
        return mapToDto(actualizado);
    }

    @Transactional(readOnly = true)
    public List<ResponseTicketDto> obtenerTicketsUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new CustomNotFoundException("Usuario con ID " + usuarioId + " no encontrado.");
        }

        return ticketRepository.findByUsuarioId(usuarioId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ResponseTicketDto mapToDto(Tiket ticket) {
        return new ResponseTicketDto(
                ticket.getId(),
                ticket.getDescripcion(),
                ticket.getEstado(),
                ticket.getPrioridad(),
                ticket.getUsuario().getId(),
                ticket.getUsuario().getNombre()
        );
    }
}
