package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Input;
import uz.developers.appwarehouse.entity.warehouseEntity.Output;

import java.sql.Timestamp;

public interface InputRepository extends JpaRepository<Input,Long> {
    Page<Input> findAllByWarehouseId(Long warehouse_id, Pageable pageable);

    Page<Input> findAllBySupplier_PhoneNumber(String supplier_phoneNumber, Pageable pageable);

    Page<Input> findAllByCurrencyId(Long currency_id, Pageable pageable);

    Input findByFactureNumber(String factureNumber);

    Page<Input> findAllByDate(Timestamp date, Pageable pageable);
}
