package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.FacturaDto;
import java.util.List;

public interface OperacionesFactura {
    public FacturaDto crear(FacturaDto facturaDto);
    public FacturaDto modificar(FacturaDto facturaDto);
    public void borrar(FacturaDto facturaDto);
    public void borrar(Integer pkEntidad);
    public List<FacturaDto> buscarTodos();
    public FacturaDto buscarID(Integer pkEntidad);
}