package bg.softuni.productshop.data.repositories;

import bg.softuni.productshop.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Set<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal from, BigDecimal to);
}
