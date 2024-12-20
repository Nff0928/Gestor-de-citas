package co.ucentral.sistemas.citasmedicas.repositorios;

import co.ucentral.sistemas.citasmedicas.entidades.Afiliacion;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositorioAfiliacion extends CrudRepository<Afiliacion,Integer>, JpaSpecificationExecutor<Afiliacion> {
    Afiliacion findByIdAfiliado(int idAfiliado);
    List<Afiliacion> findByIdConsultor(int idConsultor);
}
