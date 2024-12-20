package co.ucentral.sistemas.citasmedicas.servicios;

import co.ucentral.sistemas.citasmedicas.dto.AfiliadoDto;
import co.ucentral.sistemas.citasmedicas.entidades.Afiliado;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioAfiliado;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosAfiliado;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiciosAfiliadoTest {

    @InjectMocks
    ServiciosAfiliado serviciosAfiliado;

    @Mock
    RepositorioAfiliado repositorioAfiliado;

    @Test
    void crearTest() {
        AfiliadoDto afiliadoDto = new AfiliadoDto();
        Afiliado afiliado = new Afiliado();
        when(repositorioAfiliado.save(any(Afiliado.class))).thenReturn(afiliado);

        AfiliadoDto result = serviciosAfiliado.crear(afiliadoDto);

        verify(repositorioAfiliado, times(1)).save(any(Afiliado.class));
        assertEquals(afiliadoDto, result);
    }

    @Test
    void modificarTest() {
        AfiliadoDto afiliadoDto = new AfiliadoDto();
        afiliadoDto.setIdentificacion(1);
        Afiliado afiliado = new Afiliado();
        when(repositorioAfiliado.existsById(1)).thenReturn(true);
        when(repositorioAfiliado.save(any(Afiliado.class))).thenReturn(afiliado);

        AfiliadoDto result = serviciosAfiliado.modificar(afiliadoDto);

        verify(repositorioAfiliado, times(1)).save(any(Afiliado.class));
        assertEquals(afiliadoDto, result);
    }

    @Test
    void borrarTest() {
        serviciosAfiliado.borrar(1);

        verify(repositorioAfiliado, times(1)).deleteById(1);
    }

    @Test
    void buscarTodosTest() {
        Afiliado afiliado1 = new Afiliado();
        Afiliado afiliado2 = new Afiliado();
        List<Afiliado> afiliados = Arrays.asList(afiliado1, afiliado2);
        when(repositorioAfiliado.findAll()).thenReturn(afiliados);

        List<AfiliadoDto> result = serviciosAfiliado.buscarTodos();

        verify(repositorioAfiliado, times(1)).findAll();
        assertNotNull(result);
        assertEquals(afiliados.size(), result.size());
    }

    @Test
    void buscarIDTest() {
        Afiliado afiliado = new Afiliado();
        when(repositorioAfiliado.findById(1)).thenReturn(Optional.of(afiliado));

        Afiliado result = serviciosAfiliado.buscarID(1);

        verify(repositorioAfiliado, times(1)).findById(1);
        assertNotNull(result);
        assertEquals(afiliado, result);
    }
}