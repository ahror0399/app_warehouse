package uz.developers.appwarehouse.controller.warehouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.developers.appwarehouse.dto.WarehouseDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.WarehouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    //omborxona qo'shish
    @PostMapping
    public Result add(@RequestBody WarehouseDto warehouseDto){
        return warehouseService.add(warehouseDto);
    }

    //omborxonani no active qilish
    @DeleteMapping("/{id}")
    public Result noActive(@PathVariable Long id){
        return warehouseService.delete(id);
    }

    //omborxonani active qilish
    @PostMapping("/active/{id}")
    public Result active(@PathVariable Long id){
        return warehouseService.active(id);
    }

    //shu id li omborxonani o'zgartirish
    @PutMapping("/{id}")
    public Result edit(@PathVariable Long id,@RequestBody WarehouseDto warehouseDto){
        return warehouseService.edit(id,warehouseDto);
    }
    //shu id li omborxona
    @GetMapping("/{id}")
    public Warehouse getbyId(@PathVariable Long id){
        return warehouseService.getById(id);
    }

    //barcha omborxonalar
    @GetMapping("/all")
        public Page<Warehouse> getAll(@RequestParam int page){
        return warehouseService.getAllWareHouse(page);
    }





}
