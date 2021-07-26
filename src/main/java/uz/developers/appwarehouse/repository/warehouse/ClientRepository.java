package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
    boolean existsByName(String name);

    boolean existsByPhoneNumber(String phoneNumber);

}
