package co.ucentral.sistemas.citasmedicas.servicios;

import co.ucentral.sistemas.citasmedicas.dto.CitaDto;
import co.ucentral.sistemas.citasmedicas.entidades.Cita;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioCita;
import co.ucentral.sistemas.citasmedicas.servicios.ServiciosCita;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ServicioCitaTest {
    @InjectMocks
    ServiciosCita serviciosCita;

    @Mock
    RepositorioCita repositorioCita;

    @Mock
    ModelMapper modelMapper;

    @Test
    void crearTest() {
        CitaDto citaDto = new CitaDto();
        Cita cita = new Cita();
        when(modelMapper.map(citaDto, Cita.class)).thenReturn(cita);
        when(repositorioCita.save(cita)).thenReturn(cita);
        when(modelMapper.map(cita, CitaDto.class)).thenReturn(citaDto);

        CitaDto result = serviciosCita.crear(citaDto);

        verify(repositorioCita, times(1)).save(cita);
        assertEquals(citaDto, result);
    }


    @Test
    void modificarTest() {
        Cita cita = new Cita();
        cita.setId_cita(1);
        when(repositorioCita.findById(1)).thenReturn(Optional.of(cita));
        when(repositorioCita.save(cita)).thenReturn(cita);

        Cita result = serviciosCita.modificar(cita);

        verify(repositorioCita, times(1)).save(cita);
        assertEquals(cita, result);
    }
    @Test
    void borrarTest() {
        Cita cita = new Cita();
        cita.setId_cita(1);
        when(repositorioCita.findById(1)).thenReturn(Optional.of(cita));

        serviciosCita.borrar(1);

        verify(repositorioCita, times(1)).deleteById(1);
    }

    @Test
    void buscarTodosTest() {
        Cita cita1 = new Cita();
        Cita cita2 = new Cita();
        List<Cita> citas = Arrays.asList(cita1, cita2);
        when(repositorioCita.findAll()).thenReturn(citas);

        List<Cita> result = serviciosCita.buscarTodos();

        verify(repositorioCita, times(1)).findAll();
        assertEquals(citas, result);
    }

    @Test
    void buscarIDTest() {
        Cita cita = new Cita();
        cita.setId_cita(1);
        when(repositorioCita.findById(1)).thenReturn(Optional.of(cita));

        Cita result = serviciosCita.buscarID(1);

        verify(repositorioCita, times(1)).findById(1);
        //assertTrue(result.isPresent());
        //assertEquals(cita, result.get());
    }
}