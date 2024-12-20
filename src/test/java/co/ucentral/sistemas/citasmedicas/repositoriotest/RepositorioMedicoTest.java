package co.ucentral.sistemas.citasmedicas.repositoriotest;

import co.ucentral.sistemas.citasmedicas.entidades.Medico;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioMedico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RepositorioMedicoTest {

    @Autowired
    private RepositorioMedico repositorioMedico;

    @Test
    void testGuardarYBuscarMedico() {
        Medico medico = new Medico();
        // Configura los atributos del medico...

        // Guarda el medico
        medico = repositorioMedico.save(medico);

        // Busca el medico
       /* Medico medicoBuscado = repositorioMedico.findById(medico.getId()).orElse(null);

        // Verifica que el medico guardado y el medico buscado sean iguales
        assertEquals(medico, medicoBuscado);*/
    }

    @Test
    void testBuscarPorIdentificacion() {
        Medico medico = new Medico();
        // Configura los atributos del medico...

        // Guarda el medico
        medico = repositorioMedico.save(medico);

        // Busca el medico
        Medico medicoBuscado = repositorioMedico.findByIdentificacion(medico.getIdentificacion());

        // Verifica que el medico guardado y el medico buscado sean iguales
        assertEquals(medico, medicoBuscado);
    }
}