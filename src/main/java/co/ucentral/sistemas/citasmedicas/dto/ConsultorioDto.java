package co.ucentral.sistemas.citasmedicas.dto;
import co.ucentral.sistemas.citasmedicas.entidades.Sede;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultorioDto {

    private Integer serial;
    private Integer idConsultorio;
    private String nombre;
    private Sede sede;

}
