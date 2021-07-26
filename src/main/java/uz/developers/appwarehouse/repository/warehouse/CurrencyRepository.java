package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {
    boolean existsByName(String name);
}
