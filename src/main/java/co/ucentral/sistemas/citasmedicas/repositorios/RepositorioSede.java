package co.ucentral.sistemas.citasmedicas.repositorios;

import co.ucentral.sistemas.citasmedicas.entidades.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioSede extends JpaRepository<Sede, Integer> {

}