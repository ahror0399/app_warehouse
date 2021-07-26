package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAllByCategoryIdAndActiveIsTrue(Long category_id,  Pageable pageable);

    boolean existsByNameAndCategoryId(String name, Long category_id);
}
