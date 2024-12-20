package co.ucentral.sistemas.citasmedicas.repositoriotest;
import co.ucentral.sistemas.citasmedicas.entidades.Consultor;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioConsultor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RepositorioConsultorTest {

    @Autowired
    RepositorioConsultor repositorioConsultor;
    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("Test de Guardado")
    @Test
    void testGuardar() {

        Consultor consultor = new Consultor(1000000000,"Lucia Fernandez",true);

        Consultor consultor1 = repositorioConsultor.save(consultor);
        assertThat(consultor1).isNotNull();
        assertThat(consultor1).isNull();
        assertThat(consultor1.getIdentificacion()).isPositive();
    }

    @DisplayName("Test de Busqueda Id")
    @Test
    void testBuscarIdentificacion() {

        Consultor consultor = new Consultor(1000000000, "Lucia Fernandez", true);
        repositorioConsultor.save(consultor);

        Consultor buscado =  repositorioConsultor.findByIdentificacion(1000000000);

        assertEquals(consultor, buscado);

    }

    @DisplayName("Test de Busqueda Id y Nombre")
    @Test
    void testBuscarIdentificacionyNombre() {

        Consultor consultor = new Consultor(1000000000,"Lucia Fernandez",true);

        consultor = repositorioConsultor.save(consultor);

        Consultor buscado = repositorioConsultor.findByIdentificacionAndNombre(consultor.getIdentificacion(), consultor.getNombre());
        assertEquals(consultor, buscado);

    }
}