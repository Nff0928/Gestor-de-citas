package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.EspecialidadDto;
import java.util.List;

public interface OperacionesEspecialidad {
    public EspecialidadDto crear(EspecialidadDto especialidadDto);
    public EspecialidadDto modificar(EspecialidadDto especialidadDto);
    public void borrar(EspecialidadDto especialidadDto);
    public void borrar(Integer pkEntidad);
    public List<EspecialidadDto> buscarTodos();
    public EspecialidadDto buscarID(Integer pkEntidad);

}
