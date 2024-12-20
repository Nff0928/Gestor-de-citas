package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.MedicoDto;
import java.util.List;

public interface OperacionesMedico {

    public MedicoDto crear(MedicoDto medicoDto);
    public MedicoDto modificar(MedicoDto medicoDto);
    public void borrar(MedicoDto medicoDto);
    public void borrar(Integer pkEntidad);
    public List<MedicoDto> buscarTodos();
    public MedicoDto buscarID(Integer pkEntidad);
}
