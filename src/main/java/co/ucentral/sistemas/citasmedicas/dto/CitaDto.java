package co.ucentral.sistemas.citasmedicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CitaDto {

    private int id_cita;
    private LocalDateTime fecha;
    private int id_medico;
    private int id_afiliado;
    private int id_especialidad;
    private int id_sede;
    private int id_factura;
    private String estado;

    public void setAfiliado(AfiliadoDto afiliadoDto) {
    }
}
