package UserRepository;
 
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;


import Entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
 
	Optional<User> findByEmail(String email);
}