package mx.uam.integracion.casa.Casa.repository;

import mx.uam.integracion.casa.Casa.entity.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Long> {
}