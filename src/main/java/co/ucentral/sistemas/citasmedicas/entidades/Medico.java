package co.ucentral.sistemas.citasmedicas.entidades;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Medicos")
@ToString
@Entity
public class Medico {
    @Id
    @Column(name = "med_id", nullable = false)
    private Integer identificacion;

    @Column(name = "med_nombre", nullable = false)
    private String nombre;

    @Column(name = "med_estado", nullable = false)
    private Boolean estado;

    @OneToOne(targetEntity = Registro.class)
    @JoinColumn(name = "reg_id")
    @ToString.Exclude
    private Registro registro;

    @ManyToOne(targetEntity = Especialidad.class)
    @JoinColumn(name = "es_id")
    @ToString.Exclude
    private Especialidad especialidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @OneToOne(targetEntity = Consultorio.class)
    @JoinColumn(name = "con_id")
    @ToString.Exclude
    private Consultorio consultorio;

    @ManyToOne(targetEntity = Sede.class)
    @JoinColumn(name = "sed_id")
    @ToString.Exclude
    private Sede sede;



    public Medico(int identificacion, String nombre, boolean estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.estado = estado;
    }
    public Medico(Integer identificacion, String nombre, Boolean estado, Especialidad especialidad, Consultorio consultorio, Sede sede) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.estado = estado;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.sede = sede;

        this.rol = new Rol();
        this.rol.setIdRol(2);
    }
}