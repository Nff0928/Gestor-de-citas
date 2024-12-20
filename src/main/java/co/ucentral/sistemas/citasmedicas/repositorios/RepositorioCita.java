package co.ucentral.sistemas.citasmedicas.repositorios;

import co.ucentral.sistemas.citasmedicas.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioCita extends JpaRepository<Cita,Integer> {

    @Query("SELECT c FROM Cita c WHERE c.afiliado.identificacion = :identificacion")
    List<Cita> buscarPorAfiliado(@Param("identificacion") int identificacion);

    @Query("SELECT c FROM Cita c WHERE c.medico.identificacion = :medicoId")
    List<Cita> buscarPorMedico(@Param("medicoId") int medicoId);
}
