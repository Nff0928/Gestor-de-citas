package co.ucentral.sistemas.citasmedicas.repositorios;
import co.ucentral.sistemas.citasmedicas.entidades.Consultorio;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface RepositorioConsultorio extends CrudRepository<Consultorio, Integer>, JpaSpecificationExecutor<Consultorio> {
    Consultorio findByIdConsultorio(int idConsultorio);
}
