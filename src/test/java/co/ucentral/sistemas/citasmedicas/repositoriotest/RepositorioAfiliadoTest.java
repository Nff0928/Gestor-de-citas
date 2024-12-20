package co.ucentral.sistemas.citasmedicas.repositoriotest;

import co.ucentral.sistemas.citasmedicas.entidades.Afiliado;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioAfiliado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RepositorioAfiliadoTest {

    @Autowired
    private RepositorioAfiliado repositorioAfiliado;

    @Test
    void testGuardarYBuscarAfiliado() {
        Afiliado afiliado = new Afiliado();
        // Configura los atributos del afiliado...

        // Guarda el afiliado
        afiliado = repositorioAfiliado.save(afiliado);

        // Busca el afiliado
         Afiliado afiliadoBuscado = repositorioAfiliado.findByIdentificacion(afiliado.getIdentificacion());

        // Verifica que el afiliado guardado y el afiliado buscado sean iguales
        assertEquals(afiliado, afiliadoBuscado);
    }

    @Test
    void testBuscarPorIdentificacion() {
        Afiliado afiliado = new Afiliado();
        // Configura los atributos del afiliado...

        // Guarda el afiliado
        afiliado = repositorioAfiliado.save(afiliado);

        // Busca el afiliado
        Afiliado afiliadoBuscado = repositorioAfiliado.findByIdentificacion(afiliado.getIdentificacion());

        // Verifica que el afiliado guardado y el afiliado buscado sean iguales
        assertEquals(afiliado, afiliadoBuscado);
    }
}