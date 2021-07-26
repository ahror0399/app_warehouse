package uz.developers.appwarehouse.service.worehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.UserDto;
import uz.developers.appwarehouse.entity.warehouseEntity.User;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;
import uz.developers.appwarehouse.repository.warehouse.UserRepository;
import uz.developers.appwarehouse.repository.warehouse.WarehouseRepository;
import uz.developers.appwarehouse.results.CodeGeneratorUUID;
import uz.developers.appwarehouse.results.Result;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Result result;

    @Autowired
    CodeGeneratorUUID codeGeneratorUUID;

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result add(UserDto userDto){
        boolean b = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());

        if (b){
            result.setMessage("Sorry this phone number has ben registered !");
            result.setSuccess(false);
            return result;
        }

        List<Long> longList = new ArrayList<Long>();
        for(Integer i: userDto.getWarehouse_id()){
            longList.add(i.longValue());
        }


        List<Warehouse> warehouses = warehouseRepository.findAllById(longList);

        if (warehouses.isEmpty()){
            result.setMessage("Sorry this warehouses not found !");
            result.setSuccess(false);
            return result;
        }

        User user=new User();

        user.setActive(true);
        user.setCode(codeGeneratorUUID.getCode());
        user.setFirtName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(warehouses);
       userRepository.save(user);
        result.setMessage("Successfully saved");
        result.setSuccess(true);
        return result;




    }

    public Result edit(UserDto userDto,Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            result.setMessage("Sorry this user not found !");
            result.setSuccess(false);
            return result;
        }

        boolean b = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());

        if (b){
            result.setMessage("Sorry this phone number has ben registered !");
            result.setSuccess(false);
            return result;
        }

        List<Long> longList = new ArrayList<Long>();
        for(Integer i: userDto.getWarehouse_id()){
            longList.add(i.longValue());
        }

        List<Warehouse> warehouses = warehouseRepository.findAllById(longList);

        if (warehouses.isEmpty()){
            result.setMessage("Sorry this warehouses not found !");
            result.setSuccess(false);
            return result;
        }

        User user= optionalUser.get();

        user.setActive(true);
        user.setCode(codeGeneratorUUID.getCode());
        user.setFirtName(user.getFirtName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(warehouses);
        userRepository.save(user);
        result.setMessage("Successfully edited");
        result.setSuccess(true);
        return result;


    }

    public Result delete(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            result.setMessage("user not found");
            result.setSuccess(false);
            return result;
        }

        if (!optionalUser.get().isActive()) {
            result.setMessage("this user no active");
            result.setSuccess(false);
            return result;
        }
        User user= optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
        result.setMessage("Succesfully no actived");
        result.setSuccess(true);
        return result;


    }

    public User getById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public Page<User> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        return userRepository.findAll(pageable);
    }

    public Page<User> getByWarehouseId(int page,Long id){

        List<Warehouse> warehouses = warehouseRepository.findAllById(Collections.singleton(id));
        if (warehouses.isEmpty()){
            return null;
        }

        Pageable pageable= PageRequest.of(page,10);
        Page<User> users = userRepository.findByWarehouseId(id, pageable);
        return users;


    }

    public Result active(Long id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            result.setMessage("user not found");
            result.setSuccess(false);
            return result;
        }
        user.get().setActive(true);
        userRepository.save(user.get());
        result.setMessage("user successfully actived");
        result.setSuccess(true);
        return result;

    }





}
