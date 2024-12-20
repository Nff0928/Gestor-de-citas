package co.ucentral.sistemas.citasmedicas.repositoriotest;
import co.ucentral.sistemas.citasmedicas.entidades.Rol;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioRol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class RepositorioRolTest {

    @Autowired
    RepositorioRol repositorioRol;
    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("Test de Guardado")
    @Test
    void testGuardar() {

        Rol rol = new Rol("Medico");

        Rol rol1 = repositorioRol.save(rol);
        assertThat(rol1).isNotNull();
        assertThat(rol1).isNull();
        assertThat(rol1.getIdRol()).isPositive();
    }
}