package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.developers.appwarehouse.entity.warehouseEntity.Category;
import uz.developers.appwarehouse.entity.warehouseEntity.Output_Product;

import java.time.LocalDate;
import java.util.List;

public interface OutputProductRepository extends JpaRepository<Output_Product,Long> {


    Page<Output_Product> findAllByProductId(Long id, Pageable pageable);
//
//    @Query(value = "select * from output_product where  EXTRACT(day from AGE(expire_date, now()))<:day1 ", nativeQuery = true)
//    Page<Output_Product> findAllByExpireDate(Pageable pageable, int day1);

    Page<Output_Product> findAllByProduct_CategoryOrPriceBetween(Category category, double price1, double price2, Pageable pageable);




    @Query(value = "select amount , p.name, price*amount as  all_Mony,c.name as currency from output_product ip " +
            "join product p on p.id=ip.product_id " +
            "join output on output.id=ip.output_id " +
            "join currency c on c.id=input.currency_id" +
            " where date(output.date)= ?", nativeQuery = true)
    List<?> getinputProductForDay(LocalDate day1, Pageable pageable);

}
