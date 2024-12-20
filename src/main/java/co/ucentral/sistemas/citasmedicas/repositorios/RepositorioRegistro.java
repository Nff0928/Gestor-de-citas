package co.ucentral.sistemas.citasmedicas.repositorios;
import co.ucentral.sistemas.citasmedicas.entidades.Registro;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioRegistro extends CrudRepository<Registro, Integer>, JpaSpecificationExecutor<Registro> {
    Registro findByIdUsuarioAndContrasenia(int idUsuario, String contrasenia);

    Registro findByIdUsuario(int idUsuario);
    boolean existsByIdUsuario(int idUsuario);
}
