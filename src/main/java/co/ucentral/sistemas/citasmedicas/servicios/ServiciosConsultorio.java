package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.ConsultorioDto;
import co.ucentral.sistemas.citasmedicas.entidades.Consultorio;
import co.ucentral.sistemas.citasmedicas.entidades.Sede;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesConsultorio;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioConsultorio;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioEspecialidad;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioSede;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiciosConsultorio implements OperacionesConsultorio {
    ModelMapper modelMapper;
    RepositorioConsultorio repositorioConsultorio;
    RepositorioSede repositorioSede;
    RepositorioEspecialidad repositorioEspecialidad;

    public ServiciosConsultorio(ModelMapper modelMapper, RepositorioConsultorio repositorioConsultorio, RepositorioSede repositorioSede, RepositorioEspecialidad repositorioEspecialidad) {
        this.modelMapper = modelMapper;
        this.repositorioConsultorio = repositorioConsultorio;
        this.repositorioSede = repositorioSede;
        this.repositorioEspecialidad = repositorioEspecialidad;
    }

    @Override
    public ConsultorioDto crear(ConsultorioDto consultorioDto) {

        Sede sede = repositorioSede.findById(consultorioDto.getSede().getIdSede()).orElse(new Sede());
        if (sede == null || repositorioConsultorio.existsById(consultorioDto.getIdConsultorio()) ){
            return null;
        } else{
            Consultorio consultorio = repositorioConsultorio.save(modelMapper.map(consultorioDto, Consultorio.class));
            return modelMapper.map(consultorio, ConsultorioDto.class);
        }

    }

    @Override
    public ConsultorioDto modificar(ConsultorioDto consultorioDto) {
        if (this.repositorioConsultorio.existsById(consultorioDto.getIdConsultorio()))
            return this.crear(consultorioDto);
        else
            return null;
    }

    @Override
    public void borrar(ConsultorioDto consultorioDto) {this.repositorioConsultorio.delete(modelMapper.map(consultorioDto, Consultorio.class));}

    @Override
    public void borrar(Integer pkEntidad) {this.repositorioConsultorio.deleteById(pkEntidad);}

    @Override
    public List<ConsultorioDto> buscarTodos() {
        TypeToken<List<ConsultorioDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(this.repositorioConsultorio.findAll(), typeToken.getType());
    }

    @Override
    public ConsultorioDto buscarID(Integer pkEntidad) {
        Consultorio consultorio = this.repositorioConsultorio.findById(pkEntidad).orElse(null);
        if (consultorio != null) {
            return modelMapper.map(consultorio, ConsultorioDto.class);
        } else {
            return null;
        }
    }

    @Override
    public ConsultorioDto buscarIdConsultorio(Integer idConsultorio ) {
        Consultorio consultorio = this.repositorioConsultorio.findByIdConsultorio(idConsultorio);
        if (consultorio != null) {
            return modelMapper.map(consultorio, ConsultorioDto.class);
        } else {
            return null;
        }
    }
}
