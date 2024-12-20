package co.ucentral.sistemas.citasmedicas.repositorios;
import co.ucentral.sistemas.citasmedicas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioMedico extends CrudRepository<Medico, Integer>, JpaSpecificationExecutor<Medico> {
    Medico findByIdentificacionAndNombre(int identificacion, String nombre);
    Medico findByIdentificacion(Integer identificacion);

    boolean existsByIdentificacion(Integer identificacion);
}
