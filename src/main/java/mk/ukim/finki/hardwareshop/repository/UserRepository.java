package mk.ukim.finki.hardwareshop.repository;

import mk.ukim.finki.hardwareshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
