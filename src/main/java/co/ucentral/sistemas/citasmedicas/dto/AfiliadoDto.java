package co.ucentral.sistemas.citasmedicas.dto;
import co.ucentral.sistemas.citasmedicas.entidades.Cita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AfiliadoDto {

    private int identificacion;
    private String nombre;
    private Boolean estado;
    private RolDto rol;
    private RegistroDto registro;
    private List<Cita> cita;
    private AfiliacionDto afiliacion;
    private String motivo;
}