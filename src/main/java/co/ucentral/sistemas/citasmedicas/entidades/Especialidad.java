package co.ucentral.sistemas.citasmedicas.entidades;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Especialidades")
@ToString
@Entity
public class Especialidad {

    @Getter
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "es_id", nullable = false)
    private Integer idEspecialidad;

    @Column(name = "es_descripcion", nullable = true)
    private String nombre;

    public Especialidad(String nombre) {
        this.nombre = nombre;
    }
}