package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.AfiliacionDto;
import java.util.List;

public interface OperacionesAfiliacion {

    public AfiliacionDto crear(AfiliacionDto afiliacionDto);
    public AfiliacionDto modificar(AfiliacionDto afiliacionDto);
    public void borrar(AfiliacionDto afiliacionDto);
    public void borrar(Integer pkEntidad);
    public List<AfiliacionDto> buscarTodos();
    public AfiliacionDto buscarID(Integer pkEntidad);

    List<AfiliacionDto> buscarConsultor(Integer idConsultor);
}
