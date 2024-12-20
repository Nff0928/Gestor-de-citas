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

@Table(name = "Afiliciones")
@ToString
@Entity
public class Afiliacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "afili_id", nullable = false)
    private int idAfiliacion;

    @Column(name = "afili_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Column(name = "afili_tipo", nullable = false)
    private String tipoAfiliacion;

    @Column(name = "afili_tipoId", nullable = false)
    private String tipoId;

    @Column(name = "afi_id", nullable = false)
    private Integer idAfiliado;

    @Column(name = "afili_nombre", nullable = false)
    private String nombre;

    @Column(name = "afili_celular", nullable = false)
    private String celular;

    @Column(name = "afili_correo", nullable = false)
    private String correo;

    @Column(name = "afili_direccion", nullable = false)
    private String direccion;

    @Column(name = "afili_estado", nullable = false)
    private String estadoSolicitud;

    @Column(name = "con_id", nullable = false)
    private int idConsultor;

    public Afiliacion(String tipoAfiliacion, String tipoId, Integer idAfiliado, String nombre, String celular, String correo, String direccion, String estadoSolicitud, int idConsultor) {
        this.tipoAfiliacion = tipoAfiliacion;
        this.tipoId = tipoId;
        this.idAfiliado = idAfiliado;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
        this.direccion = direccion;
        this.estadoSolicitud = estadoSolicitud;
        this.idConsultor = idConsultor;
    }
}
