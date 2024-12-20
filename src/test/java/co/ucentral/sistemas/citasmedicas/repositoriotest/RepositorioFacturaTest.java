package co.ucentral.sistemas.citasmedicas.repositoriotest;
import co.ucentral.sistemas.citasmedicas.entidades.Factura;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioFactura;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
class RepositorioFacturaTest {

    @Autowired
    RepositorioFactura repositorioFactura;
    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("Test de Guardado")
    @Test
    void testGuardar() {

        Factura factura = new Factura();

        Factura factura1 = repositorioFactura.save(factura);
        assertThat(factura1).isNotNull();
        assertThat(factura1).isNull();
        assertThat(factura1.getIdFactura()).isPositive();
    }
}