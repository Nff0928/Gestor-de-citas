package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.CitaDto;
import co.ucentral.sistemas.citasmedicas.entidades.Afiliado;
import co.ucentral.sistemas.citasmedicas.entidades.Cita;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesCita;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioAfiliado;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioCita;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ServiciosCita implements OperacionesCita {
    private ModelMapper modelMapper = new ModelMapper();
    private final RepositorioCita repositorioCita;
    private final RepositorioAfiliado repositorioAfiliado;

    @Autowired
    public ServiciosCita(RepositorioCita repositorioCita, RepositorioAfiliado repositorioAfiliado) {
        this.repositorioCita = repositorioCita;
        this.repositorioAfiliado = repositorioAfiliado;
    }



    public void asignarCitaAAfiliado(int idCita, int idAfiliado) {
        // Buscar la cita por su ID
        Optional<Cita> citaOptional = repositorioCita.findById(idCita);
        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            // Buscar el afiliado por su ID
            Optional<Afiliado> afiliadoOptional = repositorioAfiliado.findById(idAfiliado);
            if (afiliadoOptional.isPresent()) {
                Afiliado afiliado = afiliadoOptional.get();
                // Asignar la cita al afiliado
                cita.setAfiliado(afiliado);
                // Guardar la cita actualizada en la base de datos
                repositorioCita.save(cita);
            } else {
                throw new RuntimeException("No se encontró el afiliado con ID: " + idAfiliado);
            }
        } else {
            throw new RuntimeException("No se encontró la cita con ID: " + idCita);
        }
    }

    @Override
    public CitaDto crear(CitaDto citaDto) {
        if (citaDto != null){
            Cita cita =   repositorioCita.save(modelMapper.map(citaDto, Cita.class));
            return modelMapper.map(cita, CitaDto.class);
        }

        else
            return null;
    }

    @Override
    public Cita modificar(Cita cita) {

        Optional<Cita> citaExistente = repositorioCita.findById(cita.getId_cita());
        if (citaExistente.isPresent()) {
            Cita citaActualizada = citaExistente.get();
            return repositorioCita.save(citaActualizada);
        } else {
            // Si la cita no existe en el repositorio, devuelve null o maneja el caso según lo necesites
            return null;
        }
    }


    @Override
    public void borrar(CitaDto citaDto) {this.repositorioCita.delete(modelMapper.map(citaDto, Cita.class));}

    @Override
    public void borrar(Integer pkEntidad) {this.repositorioCita.deleteById(pkEntidad);}

    @Override
    public List<Cita> buscarTodos() {
        return repositorioCita.findAll();
    }

    @Override
    public Cita buscarID(Integer pkEntidad) {
        Optional<Cita> citaOptional = repositorioCita.findById(pkEntidad);
        if (citaOptional.isPresent()) {
            return citaOptional.get();
        } else {
            return null;
        }
    }

    public List<Cita> buscarPorAfiliado(int identificacion) {
        return repositorioCita.buscarPorAfiliado(identificacion);
    }

    public List<Cita> obtenerCitasDisponibles() {
        List<Cita> todasLasCitas = repositorioCita.findAll();
        return todasLasCitas.stream()
                .filter(cita -> cita.getEstado().equals("Proceso"))
                .toList();
    }

    public List<Cita> buscarPorMedico(int medicoId) {
        return repositorioCita.buscarPorMedico(medicoId);
    }
}
