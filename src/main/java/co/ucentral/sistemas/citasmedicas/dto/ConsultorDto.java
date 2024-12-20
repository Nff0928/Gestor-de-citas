package co.ucentral.sistemas.citasmedicas.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConsultorDto {

    private int identificacion;
    private String nombre;
    private Boolean estado;
    private RolDto rol;
    private RegistroDto registro;
    private AfiliacionDto afiliacion;

}