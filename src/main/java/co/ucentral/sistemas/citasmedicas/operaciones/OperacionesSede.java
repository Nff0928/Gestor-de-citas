package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.SedeDto;


import java.util.List;


public interface OperacionesSede {
    public SedeDto crear(SedeDto sedeDto);
    public SedeDto modificar(SedeDto sedeDto);
    public void borrar(SedeDto sedeDto);
    public void borrar(Integer pkEntidad);
    public List<SedeDto> buscarTodos();
    public SedeDto buscarID(Integer pkEntidad);
}