package mk.ukim.finki.hardwareshop.repository;

import mk.ukim.finki.hardwareshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
