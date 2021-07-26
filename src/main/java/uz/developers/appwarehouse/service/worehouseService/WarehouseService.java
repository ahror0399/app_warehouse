package uz.developers.appwarehouse.service.worehouseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.WarehouseDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Warehouse;
import uz.developers.appwarehouse.repository.warehouse.WarehouseRepository;
import uz.developers.appwarehouse.results.Result;

import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    Result result;

    public Result add(WarehouseDto warehouseDto){
        boolean b = warehouseRepository.existsByName(warehouseDto.getName());
        if (b){
            result.setMessage("Sorry ! This warehouse has ben saved");
            result.setSuccess(false);
            return result;
        }
        Warehouse warehouse=new Warehouse();
        warehouse.setActive(true);
        warehouse.setName(warehouseDto.getName());
        warehouseRepository.save(warehouse);
        result.setMessage("successfully saved");
        result.setSuccess(true);
        return result;

    }

    public Result edit(Long id, WarehouseDto warehouseDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            result.setMessage("this warehouse not found");
            result.setSuccess(false);
            return result;
        }

        boolean b = warehouseRepository.existsByName(warehouseDto.getName());
        if (b){
            result.setMessage("Sorry ! This warehouse has ben saved! ");
            result.setSuccess(false);
            return result;
        }
        Warehouse warehouse= optionalWarehouse.get();
        warehouse.setActive(true);
        warehouse.setName(warehouseDto.getName());
        warehouseRepository.save(warehouse);
        result.setMessage("successfully edited");
        result.setSuccess(true);
        return result;
    }

    public Result active(Long id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            result.setMessage("this warehouse not found");
            result.setSuccess(false);
            return result;
        }
        Warehouse warehouse= optionalWarehouse.get();
        if (warehouse.isActive()) {
            result.setMessage("this warehouse ative");
            result.setSuccess(false);
            return result;
        }
        warehouse.setActive(true);
        warehouseRepository.save(warehouse);
        result.setMessage("succesfully actived");
        result.setSuccess(true);
        return result;
    }

    public Result delete(Long id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            result.setMessage("this warehouse not found");
            result.setSuccess(false);
            return result;
        }
        Warehouse warehouse= optionalWarehouse.get();
        if (!warehouse.isActive()) {
            result.setMessage("this warehouse no active");
            result.setSuccess(false);
            return result;
        }
        warehouse.setActive(false);
        warehouseRepository.save(warehouse);
        result.setMessage("succesfully no actived");
        result.setSuccess(true);
        return result;
    }

    public Warehouse getById(Long id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent()){
            return null;
        }
        return optionalWarehouse.get();

    }

    public Page<Warehouse> getAllWareHouse(int page){
        Pageable pageable= PageRequest.of(page,10);
        return warehouseRepository.findAll(pageable);
    }

}
