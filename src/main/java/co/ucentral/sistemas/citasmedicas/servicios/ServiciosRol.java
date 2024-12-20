package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.RolDto;
import co.ucentral.sistemas.citasmedicas.entidades.Rol;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesRol;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioRol;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;
import static co.ucentral.sistemas.citasmedicas.servicios.ServiciosEspecialidad.normalizar;

@Service
public class ServiciosRol implements OperacionesRol {
    ModelMapper modelMapper;
    RepositorioRol repositorioRol;

    public ServiciosRol(ModelMapper modelMapper, RepositorioRol repositorioRol) {
        this.modelMapper = modelMapper;
        this.repositorioRol = repositorioRol;
    }

    @Override
    public RolDto crear(RolDto rolDto) {

        if (rolDto.getNombre() == null || rolDto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rol no puede ser nulo o vacío");
        }
        String normalizarNombre = normalizar(rolDto.getNombre());

        if (normalizarNombre == null) {
            throw new IllegalArgumentException("El nombre normalizado del rol no puede ser nulo");
        }

        List<Rol> roles = (List<Rol>) repositorioRol.findAll();
        for (Rol rol : roles) {
            String normalizarExisteNombre = normalizar(rol.getNombre());
            if (normalizarNombre.equals(normalizarExisteNombre)) {
                return null;
            }
        }

        Rol rol = modelMapper.map(rolDto, Rol.class);
        rol = repositorioRol.save(rol);
        return modelMapper.map(rol, RolDto.class);
    }

    @Override
    public RolDto modificar(RolDto rolDto) {
        if (this.repositorioRol.existsById(rolDto.getIdRol()))
            return this.crear(rolDto);
        else
            return null;
    }

    @Override
    public void borrar(RolDto rolDto) {this.repositorioRol.delete(modelMapper.map(rolDto, Rol.class));}

    @Override
    public void borrar(Integer pkEntidad) {this.repositorioRol.deleteById(pkEntidad);}

    @Override
    public List<RolDto> buscarTodos() {
        TypeToken<List<RolDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(this.repositorioRol.findAll(), typeToken.getType());
    }

    @Override
    public RolDto buscarID(Integer pkEntidad) {
        return modelMapper.map(this.buscarID(pkEntidad), RolDto.class);
    }
}
