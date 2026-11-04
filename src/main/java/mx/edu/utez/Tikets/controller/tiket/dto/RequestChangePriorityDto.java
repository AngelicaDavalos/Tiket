package mx.edu.utez.Tikets.controller.tiket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Tikets.model.tiket.PrioridadTiket;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestChangePriorityDto {

    @NotNull(message = "La nueva prioridad es obligatoria")
    private PrioridadTiket prioridad;

    public PrioridadTiket getPrioridad() { return prioridad; }
    public void setPrioridad(PrioridadTiket prioridad) { this.prioridad = prioridad; }

}
