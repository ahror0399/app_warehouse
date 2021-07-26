package uz.developers.appwarehouse.service.worehouseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.InputDto;
import uz.developers.appwarehouse.dto.OutputDto;
import uz.developers.appwarehouse.entity.warehouseEntity.*;
import uz.developers.appwarehouse.repository.warehouse.*;
import uz.developers.appwarehouse.results.CodeGeneratorUUID;
import uz.developers.appwarehouse.results.Result;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

   @Autowired
   ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CodeGeneratorUUID codeGeneratorUUID;

    @Autowired
    Result result;

    public Result add(OutputDto outputDto) {
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClient_id());
        if (!optionalClient.isPresent()) {
            result.setSuccess(false);
            result.setMessage("client not found");
            return result;
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouse_id());
        if (!optionalWarehouse.isPresent()) {
            result.setSuccess(false);


            result.setMessage("warehouse not found");
            return result;
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrency_id());
        if (!optionalCurrency.isPresent()) {
            result.setSuccess(false);
            result.setMessage("currency not found");
            return result;
        }


       Output output=new Output();
        output.setCode(codeGeneratorUUID.getCode());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(codeGeneratorUUID.getCode());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
       outputRepository.save(output);

        result.setSuccess(true);
        result.setMessage("successfully saved");
        return result;



    }

    public Result edit(Long id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);

        if (!optionalOutput.isPresent()){
            result.setSuccess(false);
            result.setMessage("output not found");
            return result;
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClient_id());
        if (!optionalClient.isPresent()) {
            result.setSuccess(false);
            result.setMessage("client not found");
            return result;
        } Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouse_id());
        if (!optionalWarehouse.isPresent()) {
            result.setSuccess(false);


            result.setMessage("warehouse not found");
            return result;
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrency_id());
        if (!optionalCurrency.isPresent()) {
            result.setSuccess(false);
            result.setMessage("currency not found");
            return result;
        }


        Output output=new Output();
        output.setCode(codeGeneratorUUID.getCode());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(codeGeneratorUUID.getCode());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        outputRepository.save(output);

        result.setSuccess(true);
        result.setMessage("successfully edited");
        return result;





    }

    public Page<Output> getByWarehouseId(Long id,int page){
        Pageable pageable= PageRequest.of(page,10);
        return outputRepository.findAllByWarehouseId(id,pageable);
    }

    public Page<Output> getByClientPhoneNumber(String phoneNumber,int page){
        Pageable pageable= PageRequest.of(page,10);
        return outputRepository.findAllByClient_PhoneNumber(phoneNumber,pageable);
    }

    public Page<Output> getByCurrencyId(Long id,int page){
        Pageable pageable= PageRequest.of(page,10);
        return outputRepository.findAllByCurrencyId(id,pageable);
    }

    public Output getByFacturaNomer(String factureNomer){
        return outputRepository.findByFactureNumber(factureNomer);
    }

    public Page<Output> getByDate(Timestamp date, int page){
        Pageable pageable= PageRequest.of(page,10);
        return outputRepository.findAllByDate(date,pageable);
    }



}
