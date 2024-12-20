package co.ucentral.sistemas.citasmedicas.repositoriotest;
import co.ucentral.sistemas.citasmedicas.entidades.Registro;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioRegistro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.sql.Date;
import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@DataJpaTest
class RepositorioRegistroTest {

    @Autowired
    RepositorioRegistro repositorioRegistro;
    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("Test de Guardado")
    @Test
    void testGuardar() {

        Date date6 = Date.valueOf(Date.valueOf(LocalDate.of(2002,4,28)).toLocalDate());
        Registro registro = new Registro("Cédula de Ciudadanía",135253949,date6,"Vanesa Guayara","322523949","Calle 55 # 35 96","Femenino","Aldia@ucentral.edu.co","222");

        Registro registro1 = repositorioRegistro.save(registro);
        assertThat(registro1).isNotNull();
        assertThat(registro1).isNull();
        assertThat(registro1.getIdRegistro()).isPositive();
    }

    @DisplayName("Test de Busqueda Id")
    @Test
    void testBuscarIdUsuario() {

        Date date6 = Date.valueOf(Date.valueOf(LocalDate.of(2002,4,28)).toLocalDate());
        Registro registro = new Registro("Cédula de Ciudadanía",135253949,date6,"Vanesa Guayara","322523949","Calle 55 # 35 96","Femenino","Aldia@ucentral.edu.co","222");

        repositorioRegistro.save(registro);

        Registro buscado = repositorioRegistro.findByIdUsuario(135253949);

        assertEquals(registro, buscado);

    }

    @DisplayName("Test de Busqueda Id y Contraseña")
    @Test
    void testBuscarIdUsuarioYContraseña() {

        Date date6 = Date.valueOf(Date.valueOf(LocalDate.of(2002,4,28)).toLocalDate());
        Registro registro = new Registro("Cédula de Ciudadanía",135253949,date6,"Vanesa Guayara","322523949","Calle 55 # 35 96","Femenino","Aldia@ucentral.edu.co","222");
        repositorioRegistro.save(registro);

        Registro buscado = repositorioRegistro.findByIdUsuarioAndContrasenia(registro.getIdUsuario(), registro.getContrasenia());
        assertEquals(registro, buscado);

    }

    @DisplayName("Test de existenia Id")
    @Test
    void testBExisteIdUsuario() {

        Date date6 = Date.valueOf(Date.valueOf(LocalDate.of(2002,4,28)).toLocalDate());
        Registro registro = new Registro("Cédula de Ciudadanía",135253949,date6,"Vanesa Guayara","322523949","Calle 55 # 35 96","Femenino","Aldia@ucentral.edu.co","222");
        repositorioRegistro.save(registro);

        boolean existe = repositorioRegistro.existsByIdUsuario(135253949);

        assertTrue(existe);

    }
}