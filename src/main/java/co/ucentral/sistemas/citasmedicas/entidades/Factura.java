package co.ucentral.sistemas.citasmedicas.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Facturas")
@ToString
@Entity
public class Factura {

    @Id
    @Column(name = "fac_id", nullable = false)
    private int idFactura;

    @Column(name = "fac_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Column(name = "fac_valor", nullable = true, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal valor;

    @Column(name = "fac_estado", nullable = false)
    private Boolean estado;

    @OneToOne(mappedBy = "factura", cascade = CascadeType.PERSIST)
    private Cita cita;
}