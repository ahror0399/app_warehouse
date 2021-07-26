package uz.developers.appwarehouse.controller.warehouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.developers.appwarehouse.dto.InputDto;
import uz.developers.appwarehouse.dto.OutputDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Input;
import uz.developers.appwarehouse.entity.warehouseEntity.Output;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.InputService;
import uz.developers.appwarehouse.service.worehouseService.OutputService;

import java.sql.Timestamp;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    //chiqim qo'shish
    @PostMapping
    public Result add(@RequestBody OutputDto outputDto){
        return outputService.add(outputDto);
    }

    //kiritilgan chiqimni o'zgartirish
    @PutMapping("/{id}")
    public Result edit(@RequestBody OutputDto outputDto,@PathVariable Long id){
        return outputService.edit(id,outputDto);
    }
    //shu id li omborxonaga tegishli bo'lgan barcha chiqimlar
    @GetMapping("/byWarehouseId/{id}")
    public Page<Output> getByWareHouseid(@PathVariable Long id, @RequestParam int page){
        return outputService.getByWarehouseId(id,page);
    }
    //shu tel nomerli clientga tegishli barcha chiqimlar
    @GetMapping("/byClientPhoneNumber/{phoneNumber}")
    public Page<Output> getBySupplierPhone(@PathVariable String phoneNumber,@RequestParam int page){
        return outputService.getByClientPhoneNumber(phoneNumber,page);
    }
    //shu valyutaga tegishli marcha chiqimlar
    @GetMapping("/byCurrencyId/{id}")
    public Page<Output> getByCurrencyId(@PathVariable Long id,@RequestParam int page){
        return outputService.getByCurrencyId(id,page);
    }
    //shu factura nomeriga tegishli chiqim
    @GetMapping("/byFactureNomer/{factureNomer}")
    public Output getByFactureNomer(@PathVariable String factureNomer){
        return outputService.getByFacturaNomer(factureNomer);
    }

    //shu kundagi barcha chiqimlar
    @GetMapping("/byDate/{date}")
    public Page<Output> getByDate(@PathVariable Timestamp date, @RequestParam int page){
        return outputService.getByDate(date,page);
    }



}
