package mx.uam.integracion.casa.Casa.repository;

import mx.uam.integracion.casa.Casa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}