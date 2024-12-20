package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.ConsultorDto;
import co.ucentral.sistemas.citasmedicas.dto.RegistroDto;
import co.ucentral.sistemas.citasmedicas.dto.RolDto;
import co.ucentral.sistemas.citasmedicas.entidades.*;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesConsultor;
import co.ucentral.sistemas.citasmedicas.repositorios.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ServiciosConsultor implements OperacionesConsultor {
    ModelMapper modelMapper;
    RepositorioConsultor repositorioConsultor;
    RepositorioRegistro repositorioRegistro;
    RepositorioRol repositorioRol;
    RepositorioAfiliado repositorioAfiliado;
    RepositorioMedico repositorioMedico;

    public ServiciosConsultor(ModelMapper modelMapper, RepositorioConsultor repositorioConsultor, RepositorioRegistro repositorioRegistro, RepositorioRol repositorioRol, RepositorioAfiliado repositorioAfiliado, RepositorioMedico repositorioMedico) {
        this.modelMapper = modelMapper;
        this.repositorioConsultor = repositorioConsultor;
        this.repositorioRegistro = repositorioRegistro;
        this.repositorioRol = repositorioRol;
        this.repositorioAfiliado = repositorioAfiliado;
        this.repositorioMedico = repositorioMedico;
    }

    @Override
    public ConsultorDto crear(ConsultorDto consultorDto) {

        if (consultorDto == null) {
            return null;
        }

        Medico medico = repositorioMedico.findByIdentificacion(consultorDto.getIdentificacion());
        Afiliado afiliado = repositorioAfiliado.findByIdentificacion(consultorDto.getIdentificacion());

        if (medico != null || afiliado != null) {
            return null;
        }
        Rol rol = repositorioRol.findById(1).orElse(null);

        if (rol == null) {
            return consultorDto;
        }
        RolDto rolDto = new RolDto();
        rolDto.setIdRol(rol.getIdRol());
        consultorDto.setRol(rolDto);

        if (repositorioConsultor.existsByIdentificacion(consultorDto.getIdentificacion())) {
            return null;
        }

        Consultor consultor = repositorioConsultor.save(modelMapper.map(consultorDto, Consultor.class));
        return modelMapper.map(consultor, ConsultorDto.class);
    }
    @Override
    public ConsultorDto modificar(ConsultorDto consultorDto) {
        if (this.repositorioConsultor.existsById(consultorDto.getIdentificacion()))
            return this.crear(consultorDto);
        else
            return null;
    }
    @Override
    public void borrar(ConsultorDto consultorDto) {this.repositorioConsultor.delete(modelMapper.map(consultorDto, Consultor.class));}
    @Override
    public void borrar(Integer pkEntidad) {this.repositorioConsultor.deleteById(pkEntidad);}
    @Override
    public List<ConsultorDto> buscarTodos() {
        TypeToken<List<ConsultorDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(this.repositorioConsultor.findAll(), typeToken.getType());
    }
    @Override
    public ConsultorDto buscarID(Integer pkEntidad) {
        return modelMapper.map(this.buscarID(pkEntidad), ConsultorDto.class);
    }

    public ConsultorDto obtenerIdRegistro(ConsultorDto consultorDto) {
        Registro registro = repositorioRegistro.findByIdUsuario(consultorDto.getIdentificacion());

        if (registro != null) {
            RegistroDto registroDto = modelMapper.map(registro, RegistroDto.class);
            consultorDto.setRegistro(registroDto);

            return consultorDto;
        } else {
            return null;
        }
    }
}
