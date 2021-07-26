package uz.developers.appwarehouse.controller.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.developers.appwarehouse.dto.UserDto;
import uz.developers.appwarehouse.dto.WarehouseDto;
import uz.developers.appwarehouse.entity.warehouseEntity.User;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.UserService;
import uz.developers.appwarehouse.service.worehouseService.WarehouseService;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    //ishchi qo'shish
    @PostMapping
    public Result add(@RequestBody UserDto userDto){

        return userService.add(userDto);
    }
    //shu idli ishchini no active qilish
    @DeleteMapping("/{id}")
    public Result noActive(@PathVariable Long id){
        return userService.delete(id);
    }

    //shu id li ishchini active qilish
    @PostMapping("/active/{id}")
    public Result active(@PathVariable Long id){
        return userService.active(id);
    }

    //shu idli ishchini o'zgartuirish
    @PutMapping("/{id}")
    public Result edit(@PathVariable Long id,@RequestBody UserDto userDto){
        return userService.edit(userDto,id);
    }

    //shu id li ishchi ma'lumotlari
    @GetMapping("/{id}")
    public User getbyId(@PathVariable Long id){
        return userService.getById(id);
    }

    //barcha ishchilar
    @GetMapping("/all")
    public Page<User> getAll(@RequestParam int page){
        return userService.getAll(page);
    }

    //omborxonaga tegishli bo'lgan ishchilar
    @GetMapping("/byWarehouseId/{id}")
    public Page<User> getByWarehouse_id(@PathVariable Long id,@RequestParam int page){
        return userService.getByWarehouseId(page, id);
    }



}
