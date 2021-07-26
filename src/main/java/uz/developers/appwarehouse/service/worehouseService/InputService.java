package uz.developers.appwarehouse.service.worehouseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.InputDto;
import uz.developers.appwarehouse.entity.warehouseEntity.*;
import uz.developers.appwarehouse.repository.warehouse.CurrencyRepository;
import uz.developers.appwarehouse.repository.warehouse.InputRepository;
import uz.developers.appwarehouse.repository.warehouse.SupplierRepository;
import uz.developers.appwarehouse.repository.warehouse.WarehouseRepository;
import uz.developers.appwarehouse.results.CodeGeneratorUUID;
import uz.developers.appwarehouse.results.Result;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CodeGeneratorUUID codeGeneratorUUID;

    @Autowired
    Result result;

    public Result add(InputDto inputDto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplier_id());
        if (!optionalSupplier.isPresent()) {
            result.setSuccess(false);
            result.setMessage("supplier not found");
            return result;
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouse_id());
        if (!optionalWarehouse.isPresent()) {
            result.setSuccess(false);


            result.setMessage("warehouse not found");
            return result;
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrency_id());
        if (!optionalCurrency.isPresent()) {
            result.setSuccess(false);
            result.setMessage("currency not found");
            return result;
        }


        Input input = new Input();
        input.setCode(codeGeneratorUUID.getCode());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(codeGeneratorUUID.getCode());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);

        result.setSuccess(true);
        result.setMessage("successfully saved");
        return result;



    }

    public Result edit(Long id, InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);

        if (!optionalInput.isPresent()){
            result.setSuccess(false);
            result.setMessage("input not found");
            return result;
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplier_id());
        if (!optionalSupplier.isPresent()) {
            result.setSuccess(false);
            result.setMessage("supplier not found");
            return result;
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouse_id());
        if (!optionalWarehouse.isPresent()) {
            result.setSuccess(false);


            result.setMessage("warehouse not found");
            return result;
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrency_id());
        if (!optionalCurrency.isPresent()) {
            result.setSuccess(false);
            result.setMessage("currency not found");
            return result;
        }


        Input input = optionalInput.get();
        input.setCode(codeGeneratorUUID.getCode());
        input.setCurrency(optionalCurrency.get());
        input.setFactureNumber(codeGeneratorUUID.getCode());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);

        result.setSuccess(true);
        result.setMessage("successfully edited");
        return result;




    }

    public Page<Input> getByWarehouseId(Long id,int page){
        Pageable pageable= PageRequest.of(page,10);
        return inputRepository.findAllByWarehouseId(id,pageable);
    }

    public Page<Input> getBySuplierPhoneNumber(String phoneNumber,int page){
        Pageable pageable= PageRequest.of(page,10);
        return inputRepository.findAllBySupplier_PhoneNumber(phoneNumber,pageable);
    }

    public Page<Input> getByCurrencyId(Long id,int page){
        Pageable pageable= PageRequest.of(page,10);
        return inputRepository.findAllByCurrencyId(id,pageable);
    }

    public Input getByFacturaNomer(String factureNomer){
        return inputRepository.findByFactureNumber(factureNomer);
    }
    public Page<Input> getByDate(Timestamp date, int page){
        Pageable pageable= PageRequest.of(page,10);
        return inputRepository.findAllByDate(date,pageable);
    }


}
