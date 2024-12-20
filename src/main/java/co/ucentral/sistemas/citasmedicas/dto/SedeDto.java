package co.ucentral.sistemas.citasmedicas.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SedeDto {

    private Integer idSede;
    private String nombre;
    private String direccion;
    private Integer nconsultorios;
}
