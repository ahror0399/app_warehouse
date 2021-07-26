package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Attechment;

public interface AttechmentRepository extends JpaRepository<Attechment,Long> {
}
