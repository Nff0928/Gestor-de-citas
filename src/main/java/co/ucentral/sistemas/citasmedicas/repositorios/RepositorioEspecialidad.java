package co.ucentral.sistemas.citasmedicas.repositorios;
import co.ucentral.sistemas.citasmedicas.entidades.Especialidad;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioEspecialidad extends CrudRepository<Especialidad, Integer>, JpaSpecificationExecutor<Especialidad> {
    Especialidad findByIdEspecialidad(int idEspecialidad);
}
