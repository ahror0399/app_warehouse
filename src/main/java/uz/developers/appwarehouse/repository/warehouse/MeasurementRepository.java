package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Long> {

    boolean existsByName(String name);

}
