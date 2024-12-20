package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.RolDto;
import java.util.List;

public interface OperacionesRol {
    public RolDto crear(RolDto rolDto);
    public RolDto modificar(RolDto rolDto);
    public void borrar(RolDto rolDto);
    public void borrar(Integer pkEntidad);
    public List<RolDto> buscarTodos();
    public RolDto buscarID(Integer pkEntidad);
}
