package etf.unsa.ba.User.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import etf.unsa.ba.User.Entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
