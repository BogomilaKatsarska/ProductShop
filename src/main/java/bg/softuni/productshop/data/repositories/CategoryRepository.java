package bg.softuni.productshop.data.repositories;

import bg.softuni.productshop.data.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
