package co.ucentral.sistemas.citasmedicas.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Registros",uniqueConstraints = @UniqueConstraint(columnNames = {"reg_correo","reg_idusuario"}))
@ToString
@Entity
    public class Registro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "reg_id", nullable = false)
        private int idRegistro;

        @Column(name = "reg_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
        private LocalDateTime fechaRegistro;

        @Column(name = "reg_tipoid", nullable = false)
        private String tipoIdentificacion;

        @Column(name = "reg_idusuario", nullable = false)
        private int idUsuario;

        @Column(name = "reg_nombre", nullable = false)
        private String nombre;

        @Column(name = "reg_fechanac")
        @Temporal(TemporalType.DATE)
        private Date fechaNacimiento;

        @Column(name = "reg_celular", nullable = false)
        private String celular;

        @Column(name = "reg_direccion", nullable = false)
        private String direccion;

        @Column(name = "reg_genero", nullable = false)
        private String genero;

        @Column(name = "reg_correo", nullable = false)
        private String correo;

        @Column(name = "reg_contraseña", nullable = false)
        private String contrasenia;

        @OneToOne(mappedBy = "registro", cascade = CascadeType.PERSIST)
        private Afiliado afiliado;

        @OneToOne(mappedBy = "registro", cascade = CascadeType.PERSIST)
        private Medico medico;

        @OneToOne(mappedBy = "registro", cascade = CascadeType.PERSIST)
        private Consultor consultor;

    public Registro(int idUsuario,String contrasenia) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
    }

    public Registro(String tipoIdentificacion, int idUsuario, Date fechaNacimiento,String nombre, String celular, String direccion, String genero, String correo, String contrasenia) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.idUsuario = idUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
        this.genero = genero;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }
}
