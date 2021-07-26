package uz.developers.appwarehouse.controller.warehouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import uz.developers.appwarehouse.dto.InputProductDto;
import uz.developers.appwarehouse.dto.InputProductsForOneDay;
import uz.developers.appwarehouse.entity.warehouseEntity.Input_Product;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.InputProductService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/input/product")
public class Input_Pruduct_Controller {

    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public Result add(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.add(inputProductDto);
    }

    //yaroqlilik muddati kiritilgan kundan kam qolgan mahsulotlar
    @GetMapping("/byDateLifetime/{day}")
    public Page<Input_Product> getByDedline(
            @RequestParam int page,
            @RequestParam(required = true, defaultValue = "10") int limit,
            @PathVariable int day
    ) {


        return inputProductService.getByExpireDateLifetime(page, day);
    }

    //shu kirimdagi product id ga tegishli mahsulotlar
    @GetMapping("/byProductId/{id}")
    public Page<Input_Product> getByProductId(
            @RequestParam int page, @PathVariable Long id

    ) {
        return inputProductService.getByProductId(id, page);
    }


    // kirimdagi mahsulotlarning kategoriya id si va narxi kiritilgan ikki narx orasida bo'lgan mahsulotlar
    @GetMapping("/byCategoryIdOrPriceBeetwen/{id}")
    public Page<Input_Product> getByProductId(
            @RequestParam int page, @PathVariable Long id, @RequestParam double price1,
            @RequestParam double price2
    ) {
        return inputProductService.getInputProductByCategoryIdOrPriceBeetwen(page, price1, price2, id);
    }



    //Bir kundagi barcha kirimdagi mahsulotlar
    @GetMapping("/productforOneDay")
    public List<?> getByProductByDay(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date day, @RequestParam int page
    ) {

        LocalDate myObj = day.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return inputProductService.getInputProductForOneDay(myObj, page);
    }

    //id orqali update
    @PutMapping("/{id}")
    public Result edit(@PathVariable Long id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.edit(id, inputProductDto);
    }


}
