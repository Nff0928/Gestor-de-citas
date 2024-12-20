package co.ucentral.sistemas.citasmedicas.repositoriotest;
import co.ucentral.sistemas.citasmedicas.entidades.Consultorio;
import co.ucentral.sistemas.citasmedicas.entidades.Sede;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioConsultorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
//@RunWith(SpringRunner.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class RepositorioConsultorioTest {

    @Autowired
    RepositorioConsultorio repositorioConsultorio;
    @Autowired
    TestEntityManager testEntityManager;

    @DisplayName("Test de Guardado")
    @Test
    void testGuardar() {

        Consultorio consultorio = new Consultorio(1,"Ortopedia",new Sede("Zona norte","Calle 55",23));

        Consultorio consultorio1 = repositorioConsultorio.save(consultorio);
        assertThat(consultorio1).isNotNull();
        assertThat(consultorio1).isNull();
        assertThat(consultorio1.getIdConsultorio()).isPositive();
    }

    @DisplayName("Test de Busqueda Id")
    @Test
    void testBuscarIdentificacion() {

        Consultorio consultorio = new Consultorio(1,"Ortopedia",new Sede("Zona norte","Calle 55",23));

        repositorioConsultorio.save(consultorio);

        Consultorio buscado =  repositorioConsultorio.findByIdConsultorio(1);

        assertEquals(consultorio, buscado);

    }
}