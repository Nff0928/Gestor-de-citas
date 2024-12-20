package co.ucentral.sistemas.citasmedicas.dto;
import co.ucentral.sistemas.citasmedicas.entidades.Cita;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MedicoDto {

    private Integer identificacion;
    private String nombre;
    private RolDto rol;
    private Boolean estado;
    private ConsultorioDto consultorio;
    private EspecialidadDto especialidad;
    private SedeDto sede;
    private RegistroDto registro;
    private List<Cita> citas;

}
