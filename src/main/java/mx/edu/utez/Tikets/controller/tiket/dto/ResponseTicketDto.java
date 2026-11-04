package mx.edu.utez.Tikets.controller.tiket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Tikets.model.tiket.EstadoTiket;
import mx.edu.utez.Tikets.model.tiket.PrioridadTiket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTicketDto {
    private Long id;
    private String descripcion;
    private EstadoTiket estado;
    private PrioridadTiket prioridad;
    private Long usuarioId;
    private String nombreUsuario;

}
