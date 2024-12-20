package co.ucentral.sistemas.citasmedicas.repositoriotest;
import co.ucentral.sistemas.citasmedicas.entidades.Sede;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioSede;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class RepositorioSedeTest {

    @Autowired
    RepositorioSede repositorioSede;
    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("Test de Guardado")
    @Test
    void testGuardar() {

        Sede sede = new Sede("Zona sur","Calle 23",23);

        Sede sede1 = repositorioSede.save(sede);
        assertThat(sede1).isNotNull();
        assertThat(sede1).isNull();
        assertThat(sede1.getIdSede()).isPositive();
    }

}