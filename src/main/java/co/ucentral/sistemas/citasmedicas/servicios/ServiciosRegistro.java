package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.RegistroDto;
import co.ucentral.sistemas.citasmedicas.entidades.Afiliado;
import co.ucentral.sistemas.citasmedicas.entidades.Consultor;
import co.ucentral.sistemas.citasmedicas.entidades.Medico;
import co.ucentral.sistemas.citasmedicas.entidades.Registro;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesRegistro;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioAfiliado;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioConsultor;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioMedico;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioRegistro;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiciosRegistro implements OperacionesRegistro {
    ModelMapper modelMapper;
    RepositorioRegistro repositorioRegistro;
    RepositorioConsultor repositorioConsultor;
    RepositorioMedico repositorioMedico;
    RepositorioAfiliado repositorioAfiliado;

    @Autowired
    public ServiciosRegistro(ModelMapper modelMapper, RepositorioRegistro repositorioRegistro, RepositorioConsultor repositorioConsultor, RepositorioMedico repositorioMedico, RepositorioAfiliado repositorioAfiliado) {
        this.modelMapper = modelMapper;
        this.repositorioRegistro = repositorioRegistro;
        this.repositorioConsultor = repositorioConsultor;
        this.repositorioMedico = repositorioMedico;
        this.repositorioAfiliado = repositorioAfiliado;
    }

    @Override
    public RegistroDto crear(RegistroDto registroDto) {

        if(registroDto.getFechaRegistro() == null){
            registroDto.setFechaRegistro(LocalDateTime.now());
        }

        Consultor consultor = repositorioConsultor.findByIdentificacionAndNombre(registroDto.getIdUsuario(), registroDto.getNombre());
        Medico medico = repositorioMedico.findByIdentificacionAndNombre(registroDto.getIdUsuario(), registroDto.getNombre());
        Afiliado afiliado = repositorioAfiliado.findByIdentificacionAndNombre(registroDto.getIdUsuario(), registroDto.getNombre());

        if (consultor != null || medico != null || afiliado != null) {
            if (repositorioRegistro.existsByIdUsuario(registroDto.getIdUsuario())) {
                return null;
            } else {
                Registro registro = repositorioRegistro.save(modelMapper.map(registroDto, Registro.class));
                return modelMapper.map(registro, RegistroDto.class);
            }
        } else {
            return null;
        }
    }
    @Override
    public RegistroDto modificar(RegistroDto registroDto) {
        if (this.repositorioRegistro.existsById(registroDto.getIdRegistro()))
            return this.crear(registroDto);
        else
            return null;
    }

    @Override
    public void borrar(RegistroDto registroDto) {this.repositorioRegistro.delete(modelMapper.map(registroDto, Registro.class));}

    @Override
    public void borrar(Integer pkEntidad) {this.repositorioRegistro.deleteById(pkEntidad);}

    @Override
    public List<RegistroDto> buscarTodos() {
        TypeToken<List<RegistroDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(this.repositorioRegistro.findAll(), typeToken.getType());
    }

    @Override
    public RegistroDto buscarID(Integer pkEntidad) {
        return modelMapper.map(this.buscarID(pkEntidad), RegistroDto.class);
    }

    public boolean validarCredenciales(Integer idUsuario, String contrasenia) {
        Registro registro = repositorioRegistro.findByIdUsuarioAndContrasenia(idUsuario, contrasenia);
        return registro != null;
    }
}
