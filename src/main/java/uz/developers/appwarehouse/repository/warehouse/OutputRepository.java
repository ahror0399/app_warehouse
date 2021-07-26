package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Input;
import uz.developers.appwarehouse.entity.warehouseEntity.Output;

import java.sql.Timestamp;

public interface OutputRepository extends JpaRepository<Output,Long> {
    Page<Output> findAllByWarehouseId(Long id, Pageable pageable);


    Page<Output> findAllByClient_PhoneNumber(String phoneNumber, Pageable pageable);

    Page<Output> findAllByCurrencyId(Long id, Pageable pageable);

    Output findByFactureNumber(String factureNomer);

    Page<Output> findAllByDate(Timestamp date, Pageable pageable);

}
