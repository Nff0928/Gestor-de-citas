package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.ConsultorDto;
import java.util.List;

public interface OperacionesConsultor {

    public ConsultorDto crear(ConsultorDto consultorDto);
    public ConsultorDto modificar(ConsultorDto consultorDto);
    public void borrar(ConsultorDto consultorDto);
    public void borrar(Integer pkEntidad);
    public List<ConsultorDto> buscarTodos();
    public ConsultorDto buscarID(Integer pkEntidad);
}
