package co.ucentral.sistemas.citasmedicas.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDto {

    private int idFactura;
    private LocalDateTime fecha;
    private BigDecimal valor;
    private Boolean estado;
}
