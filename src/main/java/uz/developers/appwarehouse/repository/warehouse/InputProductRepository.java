package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.developers.appwarehouse.dto.InputProductsForOneDay;
import uz.developers.appwarehouse.entity.warehouseEntity.Category;
import uz.developers.appwarehouse.entity.warehouseEntity.Input_Product;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface InputProductRepository extends JpaRepository<Input_Product, Long> {

    Page<Input_Product> findAllByProductId(Long product_id, Pageable pageable);

    @Query(value = "select * from input_product where  EXTRACT(day from AGE(expire_date, now()))<:day1 ", nativeQuery = true)
    Page<Input_Product> findAllByExpireDate(Pageable pageable, int day1);

    Page<Input_Product> findAllByProduct_CategoryOrPriceBetween(Category product_category, Double price, Double price2, Pageable pageable);


    @Query(value = "select amount , p.name, price*amount as  all_Mony,c.name as currency from input_product ip " +
            "join product p on p.id=ip.product_id " +
            "join input on input.id=ip.input_id " +
            "join currency c on c.id=input.currency_id" +
            " where date(input.date)= ?", nativeQuery = true)
    List<?> getinputProductForDay(LocalDate date1,Pageable pageable);



}
