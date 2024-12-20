package co.ucentral.sistemas.citasmedicas.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Citas")
@ToString
@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cit_id", nullable = false)
    private int id_cita;

    @Column(name = "cit_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "es_id")
    private Especialidad especialidad;

    @Column(name = "cit_estado", nullable = true)
    private String estado;

    @Column(name = "cit_Observacion", nullable = true)
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "med_id",nullable = true)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "Afi_id",nullable = true)
    private Afiliado afiliado;

    @OneToOne
    @JoinColumn(name = "fac_id")
    private Factura factura;

    public Cita(int id_cita, LocalDateTime fecha, Especialidad especialidad, String estado, Medico medico, Factura factura) {
        this.id_cita = id_cita;
        this.fecha = fecha;
        this.especialidad = especialidad;
        this.estado = estado;
        this.medico = medico;
        this.factura = factura;
    }
}