package etf.unsa.ba.Books.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import etf.unsa.ba.Books.Entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
