package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.SedeDto;
import co.ucentral.sistemas.citasmedicas.entidades.Sede;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesSede;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioSede;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;
import static co.ucentral.sistemas.citasmedicas.servicios.ServiciosEspecialidad.normalizar;

@Service
public class ServiciosSede implements OperacionesSede {
    ModelMapper modelMapper;
    RepositorioSede repositorioSede;

    public ServiciosSede(ModelMapper modelMapper, RepositorioSede repositorioSede) {
        this.modelMapper = modelMapper;
        this.repositorioSede = repositorioSede;
    }

    @Override
    public SedeDto crear(SedeDto sedeDto) {
        String normalizarNombre = normalizar(sedeDto.getNombre());

        if (normalizarNombre == null) {
            throw new IllegalArgumentException("El nombre normalizado de la sede no puede ser nulo");
        }

        List<Sede> sedes = repositorioSede.findAll();
        for (Sede sede : sedes) {
            String normalizarExisteNombre = normalizar(sede.getNombre());
            if (normalizarNombre.equals(normalizarExisteNombre)) {
                return null;
            }
        }

        Sede sede = modelMapper.map(sedeDto, Sede.class);
        sede = repositorioSede.save(sede);
        return modelMapper.map(sede, SedeDto.class);
    }

    @Override
    public SedeDto modificar(SedeDto sedeDto) {
        if (this.repositorioSede.existsById(sedeDto.getIdSede()))
            return this.crear(sedeDto);
        else
            return null;
    }

    @Override
    public void borrar(SedeDto sedeDto) {this.repositorioSede.delete(modelMapper.map(sedeDto, Sede.class));}

    @Override
    public void borrar(Integer pkEntidad) {this.repositorioSede.deleteById(pkEntidad);}

    @Override
    public List<SedeDto> buscarTodos() {
        TypeToken<List<SedeDto>> typeToken = new TypeToken<>() {
        };
        return modelMapper.map(this.repositorioSede.findAll(), typeToken.getType());
    }

    @Override
    public SedeDto buscarID(Integer pkEntidad) {

        Sede sede = this.repositorioSede.findById(pkEntidad).orElse(null);
        if (sede != null) {
            return modelMapper.map(sede, SedeDto.class);
        } else {
            return null;
        }
    }
}
