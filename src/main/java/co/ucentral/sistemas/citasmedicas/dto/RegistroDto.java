package co.ucentral.sistemas.citasmedicas.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDto {

    private int idRegistro;
    private LocalDateTime fechaRegistro;
    private String tipoIdentificacion;
    private Integer idUsuario;
    private String nombre;
    private Date fechaNacimiento;
    private String celular;
    private String direccion;
    private String genero;
    private String correo;
    private String contrasenia;
}
