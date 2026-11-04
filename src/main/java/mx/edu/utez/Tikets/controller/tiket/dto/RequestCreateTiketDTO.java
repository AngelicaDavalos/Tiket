package mx.edu.utez.Tikets.controller.tiket.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Tikets.model.tiket.PrioridadTiket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateTiketDTO {
    @NotNull(message= "El id del usuario es obligatorio ")
    private  long usuarioId;

    @NotBlank(message = "la descripcion no puede estar vacia ")
    @Size(min = 30, max = 300, message = "la descripcion tiene que tener entre 30 y 300 caracteres ")
    private  String  descripcion;

    @NotNull(message = "La prioridad es obligatoria (BAJA,MEDIA,ALTA")
    private PrioridadTiket prioridad;

}
