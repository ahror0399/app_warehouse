package uz.developers.appwarehouse.service.worehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.CurrencyDto;
import uz.developers.appwarehouse.dto.MeasurementDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Currency;
import uz.developers.appwarehouse.entity.warehouseEntity.Measurement;
import uz.developers.appwarehouse.repository.warehouse.CurrencyRepository;
import uz.developers.appwarehouse.repository.warehouse.MeasurementRepository;
import uz.developers.appwarehouse.results.Result;

import java.util.Optional;

@Service
public class CurrencyService {


    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    Result result;

    public Result add(CurrencyDto currencyDto){
        boolean b = currencyRepository.existsByName(currencyDto.getName());
        if (b){
            result.setMessage("Sorry !This Currency has ben saved");
            result.setSuccess(false);
            return result;
        }
        Currency currency=new Currency();
        currency.setActive(true);
        currency.setName(currencyDto.getName());
        currencyRepository.save(currency);
        result.setMessage("successfully saved");
        result.setSuccess(true);
        return result;

    }

    public Result edit(Long id, CurrencyDto currencyDto){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            result.setMessage("this currency not found");
            result.setSuccess(false);
            return result;
        }

        boolean b = currencyRepository.existsByName(currencyDto.getName());
        if (b){
            result.setMessage("Sorry ! This currency has ben saved! ");
            result.setSuccess(false);
            return result;
        }
        Currency currency=optionalCurrency.get();
        //currency.setActive(true);
        currency.setName(currencyDto.getName());
        currencyRepository.save(currency);
        result.setMessage("successfully edited");
        result.setSuccess(true);
        return result;
    }

    public Result active(Long id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            result.setMessage("this Currency not found");
            result.setSuccess(false);
            return result;
        }
       Currency currency = optionalCurrency.get();

        if (currency.isActive()) {
            result.setMessage("this currency active");
            result.setSuccess(false);
            return result;
        }
        currency.setActive(true);

        currencyRepository.save(currency);
        result.setMessage("succesfully actived");
        result.setSuccess(true);
        return result;
    }

    public Result delete(Long id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            result.setMessage("this currency not found");
            result.setSuccess(false);
            return result;
        }
        Currency currency= optionalCurrency.get();
        if (!currency.isActive()) {
            result.setMessage("this currency no active");
            result.setSuccess(false);
            return result;
        }
       currency.setActive(false);
        currencyRepository.save(currency);
        result.setMessage("succesfully deleted");
        result.setSuccess(true);
        return result;
    }

    public Currency getById(Long id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()){
            return null;
        }
        return optionalCurrency.get();

    }

    public Page<Currency> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        return currencyRepository.findAll(pageable);
    }


}
