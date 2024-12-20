package co.ucentral.sistemas.citasmedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfiliacionDto {

    private Integer idAfiliacion;
    private LocalDateTime fecha;
    private String tipoAfiliacion;
    private String tipoId;
    private Integer idAfiliado;
    private MultipartFile imgDocumento;
    private String nombre;
    private MultipartFile cartaAfiliacion;
    private String celular;
    private String direccion;
    private String correo;
    private String estadoSolicitud;
    private int idConsultor;
}
