package etf.unsa.ba.User.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import etf.unsa.ba.User.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
