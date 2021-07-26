package uz.developers.appwarehouse.controller.warehouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import uz.developers.appwarehouse.dto.InputProductDto;
import uz.developers.appwarehouse.dto.OutputProductDto;
import uz.developers.appwarehouse.entity.warehouseEntity.AllProduct;
import uz.developers.appwarehouse.entity.warehouseEntity.Input_Product;
import uz.developers.appwarehouse.entity.warehouseEntity.Output_Product;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.InputProductService;
import uz.developers.appwarehouse.service.worehouseService.OutputProductService;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/output/product")
public class Output_Pruduct_Controller {


    @Autowired
    OutputProductService outputProductService;


    // bu yerdaa chiqim qo'shish
    @PostMapping
    public Result add(@RequestBody OutputProductDto outputProductDto, @RequestParam int expire_day) {
        return outputProductService.add(outputProductDto, expire_day);
    }


    // Bu yerdaa so'ralgan mahsulotning yaroqlilik muddati so'ralgan kundan yani
    //   bergan kundan ko'p  bo'lgan  mahsulotlarni olamiz
    @GetMapping("/byProductIdAndBetweenNow_ExpireDate/{id}")
    public List<AllProduct> byProductIdAndBetweenNow_ExpireDate(@RequestParam int day, @PathVariable Long id) {
        return outputProductService.getAllByProductIdBeetweeenExpireDateN(day, id);
    }

    //product id orqali chiqim qilingan mahsulotlar
    @GetMapping("/getByProductId/{id}")
    public Page<Output_Product> byProductId(@PathVariable Long id, @RequestParam int page) {
        return outputProductService.getByProductId(id, page);
    }


    //chiqimdagi mahsulotlarning kategoriya id si va narxi kiritilgan ikki narx orasida bo'lgan mahsulotlar
    @GetMapping("/byCategoryIdOrPriceBeetwen/{id}")
    public Page<Output_Product> getByProductId(
            @RequestParam int page, @PathVariable Long id, @RequestParam double price1,
            @RequestParam double price2
    ) {
        return outputProductService.getOutputProductByCategoryIdOrPriceBeetwen(page, price1, price2, id);
    }


    //Bir kundagi barcha chiqimdagi mahsulotlar
    @GetMapping("/productforOneDay")
    public List<?> getByProductByDay(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date day, @RequestParam int page
    ) {

        LocalDate myObj = day.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return outputProductService.getOutputProductForOneDay(myObj, page);
    }


}
