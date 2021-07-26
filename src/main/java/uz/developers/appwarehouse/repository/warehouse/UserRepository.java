package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.developers.appwarehouse.entity.warehouseEntity.User;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "select  *from users" +
            "    join users_warehouses uw on users.id = uw.users_id" +
            "            where uw.warehouses_id=:id", nativeQuery = true)
    Page<User> findByWarehouseId(Long id, Pageable pageable);

   // Page<User> findByWarehousesIsAndId();

}
