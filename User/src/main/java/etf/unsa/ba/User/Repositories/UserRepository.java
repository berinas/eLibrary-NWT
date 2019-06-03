package etf.unsa.ba.User.Repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import etf.unsa.ba.User.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findUserById(Long userId); 
	public Iterable<User> findByFirstName(String first_name);
	public Iterable<User> findByLastName(String last_name);
	public Optional<User> findByEmail(String email);
	public Optional<User> findByUsername(String username);
    
}