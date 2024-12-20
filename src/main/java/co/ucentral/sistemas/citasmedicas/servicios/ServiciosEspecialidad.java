package co.ucentral.sistemas.citasmedicas.servicios;
import co.ucentral.sistemas.citasmedicas.dto.EspecialidadDto;
import co.ucentral.sistemas.citasmedicas.entidades.Especialidad;
import co.ucentral.sistemas.citasmedicas.operaciones.OperacionesEspecialidad;
import co.ucentral.sistemas.citasmedicas.repositorios.RepositorioEspecialidad;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.text.Normalizer;
import java.util.List;

@Service
public class ServiciosEspecialidad implements OperacionesEspecialidad {
    ModelMapper modelMapper;
    RepositorioEspecialidad repositorioEspecialidad;

    public ServiciosEspecialidad(ModelMapper modelMapper, RepositorioEspecialidad repositorioEspecialidad) {
        this.modelMapper = modelMapper;
        this.repositorioEspecialidad = repositorioEspecialidad;
    }


    @Override
    public EspecialidadDto crear(EspecialidadDto especialidadDto) {

        if (especialidadDto.getNombre() == null || especialidadDto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la especialidad no puede ser nulo o vacío");
        }
        String normalizarNombre = normalizar(especialidadDto.getNombre());

        if (normalizarNombre == null) {
            throw new IllegalArgumentException("El nombre normalizado de la especialidad no puede ser nulo");
        }

        List<Especialidad> especialidades = (List<Especialidad>) repositorioEspecialidad.findAll();
        for (Especialidad especialidad : especialidades) {
            String normalizarExisteNombre = normalizar(especialidad.getNombre());
            if (normalizarNombre.equals(normalizarExisteNombre)) {
                return null;
            }
        }

        Especialidad especialidad = modelMapper.map(especialidadDto, Especialidad.class);
        especialidad = repositorioEspecialidad.save(especialidad);
        return modelMapper.map(especialidad, EspecialidadDto.class);
    }

    @Override
    public EspecialidadDto modificar(EspecialidadDto especialidadDto) {
        if (this.repositorioEspecialidad.existsById(especialidadDto.getIdEspecialidad()))
            return this.crear(especialidadDto);
        else
            return null;
    }

    @Override
    public void borrar(EspecialidadDto especialidadDto) {this.repositorioEspecialidad.delete(modelMapper.map(especialidadDto, Especialidad.class));}

    @Override
    public void borrar(Integer pkEntidad) {this.repositorioEspecialidad.deleteById(pkEntidad);}

    @Override
    public List<EspecialidadDto> buscarTodos() {
        TypeToken<List<EspecialidadDto>> typeToken = new TypeToken<>() {};
        return modelMapper.map(this.repositorioEspecialidad.findAll(), typeToken.getType());
    }

    @Override
    public EspecialidadDto buscarID(Integer pkEntidad) {
        Especialidad especialidad = this.repositorioEspecialidad.findById(pkEntidad).orElse(null);
        if (especialidad != null) {
            return modelMapper.map(especialidad, EspecialidadDto.class);
        } else {
            return null;
        }
    }


    public static String normalizar(String cadena) {
        if (cadena == null) {
                return null;
        }
        String trimmed = cadena.trim();
        String lowerCase = trimmed.toLowerCase();
        String normalizar = Normalizer.normalize(lowerCase, Normalizer.Form.NFD);
        return normalizar.replaceAll("\\p{M}", "");
    }

}
