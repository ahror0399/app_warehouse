package uz.developers.appwarehouse.controller.warehouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.developers.appwarehouse.dto.SupplierDto;
import uz.developers.appwarehouse.dto.WarehouseDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Supplier;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.SupplierService;
import uz.developers.appwarehouse.service.worehouseService.WarehouseService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    //taminotchi qo'shish
    @PostMapping
    public Result add(@RequestBody SupplierDto supplierDto){
        return supplierService.add(supplierDto);

    }
    //taminotchini no active qilish
    @DeleteMapping("/{id}")
    public Result noActive(@PathVariable Long id){
        return supplierService.delete(id);
    }

    //taminotchini active qilish
    @PostMapping("/active/{id}")
    public Result active(@PathVariable Long id){
        return supplierService.active(id);
    }

    //taminotchining malumotlarini o'zgartirish
    @PutMapping("/{id}")
    public Result edit(@PathVariable Long id,@RequestBody SupplierDto supplierDto){
        return supplierService.edit(id,supplierDto);
    }
    //shu id li taminotchini olish
    @GetMapping("/{id}")
    public Supplier getbyId(@PathVariable Long id){
        return supplierService.getById(id);
    }

    //barcha taminotchilar
    @GetMapping("/all")
    public Page<Supplier> getAll(@RequestParam int page){
        return supplierService.getAll(page);
    }





}
