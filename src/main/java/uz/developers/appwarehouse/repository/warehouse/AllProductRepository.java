package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.developers.appwarehouse.entity.warehouseEntity.AllProduct;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AllProductRepository extends JpaRepository<AllProduct,Long> {


    @Query(value = "select * from all_product where product_id=:id and expire_date=:date1",nativeQuery = true)
   Optional< AllProduct> findByProductIdAndExpire_date(Long id, Date date1);


    @Query(value = "select * from all_product where product_id=:id and (date_part('day', ( expire_date-now())) > :day)",nativeQuery = true)
    List<AllProduct> getByProductIdAndExpireDateBetweenNow(Integer day,Long id);



}
