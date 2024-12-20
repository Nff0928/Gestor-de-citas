package co.ucentral.sistemas.citasmedicas.operaciones;
import co.ucentral.sistemas.citasmedicas.dto.RegistroDto;
import java.util.List;

public interface OperacionesRegistro {
    public RegistroDto crear(RegistroDto registroDto);
    public RegistroDto modificar(RegistroDto registroDto);
    public void borrar(RegistroDto registroDto);
    public void borrar(Integer pkEntidad);
    public List<RegistroDto> buscarTodos();
    public RegistroDto buscarID(Integer pkEntidad);
    public boolean validarCredenciales(Integer idUsuario, String contrasenia);

}
