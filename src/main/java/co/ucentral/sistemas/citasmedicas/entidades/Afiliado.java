package co.ucentral.sistemas.citasmedicas.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Afiliados")
@ToString
@Entity
public class Afiliado {

    @Id
    @Column(name = "afi_id", nullable = false)
    private int identificacion;

    @Column(name = "afi_nombre", nullable = false)
    private String nombre;

    @Column(name = "afi_estado", nullable = false)
    private Boolean estado;

    @OneToOne(targetEntity = Registro.class)
    @JoinColumn(name = "reg_id")
    private Registro registro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ToString.Exclude
    @OneToMany(mappedBy = "afiliado", fetch = FetchType.LAZY)
    private List<Cita> cita;

    @OneToOne(targetEntity = Afiliacion.class)
    @JoinColumn(name = "afili_id")
    private Afiliacion afiliacion;

    @Column(name = "afi_motivo", nullable = false)
    private String motivo;

    public Afiliado(int identificacion, String nombre, boolean estado, String motivo) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.estado = estado;
        this.motivo = motivo;

        this.rol = new Rol();
        this.rol.setIdRol(3);
    }
}