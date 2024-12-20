package co.ucentral.sistemas.citasmedicas.repositoriotest;
import co.ucentral.sistemas.citasmedicas.entidades.Especialidad;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioEspecialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class RepositorioEspecialidadTest {

    @Autowired
    RepositorioEspecialidad repositorioEspecialidad;
    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("Test de Guardado")
    @Test
    void testGuardar() {

        Especialidad especialidad = new Especialidad(1,"Ortopedia");

        Especialidad especialidad1 = repositorioEspecialidad.save(especialidad);
        assertThat(especialidad1).isNotNull();
        assertThat(especialidad1).isNull();
        assertThat(especialidad1.getIdEspecialidad()).isPositive();
    }

    @DisplayName("Test de Busqueda Id")
    @Test
    void testBuscarIdentificacion() {

        Especialidad especialidad = new Especialidad(1, "Ortopedia");
        repositorioEspecialidad.save(especialidad);

        Especialidad buscada =  repositorioEspecialidad.findByIdEspecialidad(1);

        assertEquals(especialidad, buscada);

    }
}