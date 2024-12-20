package co.ucentral.sistemas.citasmedicas.entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Consultores")
@ToString
@Entity
public class Consultor {

    @Id
    @Column(name = "c_id", nullable = false)
    private int identificacion;

    @Column(name = "c_nombre", nullable = false)
    private String nombre;

    @Column(name = "c_estado", nullable = false)
    private Boolean estado;

    @OneToOne(targetEntity = Registro.class)
    @JoinColumn(name = "reg_id")
    private Registro registro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @ManyToOne(targetEntity = Afiliacion.class)
    @JoinColumn(name = "afili_id")
    private Afiliacion afiliacion;

    public Consultor(int identificacion, String nombre, Boolean estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.estado = estado;

        this.rol = new Rol();
        this.rol.setIdRol(1);
    }
}