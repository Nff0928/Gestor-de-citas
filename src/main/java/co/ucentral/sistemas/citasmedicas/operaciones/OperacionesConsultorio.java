package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.ConsultorioDto;
import java.util.List;

public interface OperacionesConsultorio {
    public ConsultorioDto crear(ConsultorioDto consultorioDto);
    public ConsultorioDto modificar(ConsultorioDto consultorioDto);
    public void borrar(ConsultorioDto consultorioDto);
    public void borrar(Integer pkEntidad);
    public List<ConsultorioDto> buscarTodos();
    public ConsultorioDto buscarID(Integer pkEntidad);
    public ConsultorioDto buscarIdConsultorio(Integer idConsultorio );
}
