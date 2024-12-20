package co.ucentral.sistemas.citasmedicas.entidades;

import co.ucentral.sistemas.citasmedicas.dto.AfiliadoDto;
import co.ucentral.sistemas.citasmedicas.dto.ConsultorDto;
import co.ucentral.sistemas.citasmedicas.dto.MedicoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Roles")
@Entity
public class Rol {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_seq")
        @SequenceGenerator(name = "rol_seq", sequenceName = "roles_seq", allocationSize = 1)
        @Column(name = "rol_id", nullable = false)
        private int idRol;

        @Column(name = "rol_nombre", nullable = false)
        private String nombre;

        @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
        @ToString.Exclude
        private List<Afiliado> afiliados;

        @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
        @ToString.Exclude
        private List<Medico> medicos;

        @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
        @ToString.Exclude
        private List<Consultor> consultores;

        public Rol(String nombre) {
            this.nombre = nombre;
        }

}