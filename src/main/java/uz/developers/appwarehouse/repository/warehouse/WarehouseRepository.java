package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
    boolean existsByName(String name);

}
