package uz.developers.appwarehouse.controller.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.developers.appwarehouse.dto.MeasurementDto;
import uz.developers.appwarehouse.dto.WarehouseDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Measurement;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.MeasuremenetService;
import uz.developers.appwarehouse.service.worehouseService.WarehouseService;


@RestController
@RequestMapping("/measurement")
public class MeasurementContrller {

    @Autowired
    MeasuremenetService measuremenetService;

    //o'lchov birligi qo'shish
    @PostMapping
    public Result add(@RequestBody MeasurementDto measurementDto) {
        return measuremenetService.add(measurementDto);
    }

    //o'lchov birligini no avtive qilish
    @DeleteMapping("/{id}")
    public Result noActive(@PathVariable Long id) {
        return measuremenetService.delete(id);
    }

    //o'lchov birligini activa qilish
    @PostMapping("/active/{id}")
    public Result active(@PathVariable Long id) {
        return measuremenetService.active(id);
    }

    //shu id li o'lchov birligini edit
    @PutMapping("/{id}")
    public Result edit(@PathVariable Long id, @RequestBody MeasurementDto measurementDto) {
        return measuremenetService.edit(id, measurementDto);
    }

    //shu id li o'lchov
    @GetMapping("/{id}")
    public Measurement getbyId(@PathVariable Long id) {
        return measuremenetService.getById(id);
    }

    //barchha o'lchov birliklari
    @GetMapping("/all")
    public Page<Measurement> getAll(@RequestParam int page) {
        return measuremenetService.getAll(page);
    }


}
