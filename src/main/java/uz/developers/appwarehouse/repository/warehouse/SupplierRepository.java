package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    boolean existsByName(String name);
    boolean existsByPhoneNumber(String phoneNumber);

}
