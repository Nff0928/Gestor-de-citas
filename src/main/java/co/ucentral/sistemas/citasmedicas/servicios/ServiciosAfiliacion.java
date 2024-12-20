package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.AfiliacionDto;
import co.ucentral.sistemas.citasmedicas.dto.ConsultorDto;
import co.ucentral.sistemas.citasmedicas.entidades.Afiliacion;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesAfiliacion;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioAfiliacion;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class ServiciosAfiliacion implements OperacionesAfiliacion {

    ModelMapper modelMapper;
    RepositorioAfiliacion repositorioAfiliacion;
    ServiciosConsultor serviciosConsultor;

    public ServiciosAfiliacion(ModelMapper modelMapper, RepositorioAfiliacion repositorioAfiliacion, ServiciosConsultor serviciosConsultor) {
        this.modelMapper = modelMapper;
        this.repositorioAfiliacion = repositorioAfiliacion;
        this.serviciosConsultor = serviciosConsultor;
    }

    @Override
    public AfiliacionDto crear(AfiliacionDto afiliacionDto) {

        if (afiliacionDto == null) {
            return null;
        }

        afiliacionDto.setEstadoSolicitud("Nuevo");

        ConsultorDto consultor = seleccionarConsultorAleatorio();
        if (consultor != null) {
            afiliacionDto.setIdConsultor(consultor.getIdentificacion());
        }
        Afiliacion afiliacion = repositorioAfiliacion.save(modelMapper.map(afiliacionDto, Afiliacion.class));
        return modelMapper.map(afiliacion, AfiliacionDto.class);
    }

    @Override
    public AfiliacionDto modificar(AfiliacionDto afiliacionDto) {
        if (this.repositorioAfiliacion.existsById(afiliacionDto.getIdAfiliacion()))
            return this.crear(afiliacionDto);
        else
            return null;
    }

    @Override
    public void borrar(AfiliacionDto afiliacionDto) {
        this.repositorioAfiliacion.delete(modelMapper.map(repositorioAfiliacion, Afiliacion.class));
    }

    @Override
    public void borrar(Integer pkEntidad) {
        this.repositorioAfiliacion.deleteById(pkEntidad);
    }

    @Override
    public List<AfiliacionDto> buscarTodos() {
        TypeToken<List<AfiliacionDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(this.repositorioAfiliacion.findAll(), typeToken.getType());
    }

    @Override
    public AfiliacionDto buscarID(Integer pkEntidad) {
        return modelMapper.map(this.buscarID(pkEntidad), AfiliacionDto.class);
    }

    private ConsultorDto seleccionarConsultorAleatorio() {
        List<ConsultorDto> consultores = serviciosConsultor.buscarTodos();
        if (consultores.isEmpty()) {
            return null;
        }
        int aleatorio = new Random().nextInt(consultores.size());
        return consultores.get(aleatorio);
    }

    @Override
    public List<AfiliacionDto> buscarConsultor(Integer idConsultor) {
        List<Afiliacion> afiliaciones = repositorioAfiliacion.findByIdConsultor(idConsultor);
        return modelMapper.map(afiliaciones, new TypeToken<List<AfiliacionDto>>() {}.getType());
    }
}
