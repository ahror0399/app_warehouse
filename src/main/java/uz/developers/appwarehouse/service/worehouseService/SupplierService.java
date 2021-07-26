package uz.developers.appwarehouse.service.worehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.CurrencyDto;
import uz.developers.appwarehouse.dto.SupplierDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Currency;
import uz.developers.appwarehouse.entity.warehouseEntity.Supplier;
import uz.developers.appwarehouse.repository.warehouse.CurrencyRepository;
import uz.developers.appwarehouse.repository.warehouse.SupplierRepository;
import uz.developers.appwarehouse.results.Result;

import java.util.Optional;

@Service
public class SupplierService {


    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    Result result;

    public Result add(SupplierDto supplierDto){
        boolean b = supplierRepository.existsByName(supplierDto.getName());
        if (b){
            result.setMessage("Sorry !This supplier has ben saved");
            result.setSuccess(false);
            return result;
        }
        boolean b1 = supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber());

        if (b1){
            result.setMessage("Sorry !This phone Number has ben saved");
            result.setSuccess(false);
            return result;
        }

        Supplier supplier=new Supplier();
        supplier.setActive(true);
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());



        supplierRepository.save(supplier);
        result.setMessage("successfully saved");
        result.setSuccess(true);
        return result;

    }

    public Result edit(Long id, SupplierDto supplierDto){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()){
            result.setMessage("this suppliar not found");
            result.setSuccess(false);
            return result;
        }

        boolean b = supplierRepository.existsByName(supplierDto.getName());
        if (b){
            result.setMessage("Sorry ! This supplier has ben saved! ");
            result.setSuccess(false);
            return result;
        }

        boolean b1 = supplierRepository.existsByPhoneNumber(supplierDto.getPhoneNumber());

        if (b1){
            result.setMessage("Sorry !This phone Number has ben saved");
            result.setSuccess(false);
            return result;
        }
        Supplier supplier=optionalSupplier.get();
        //currency.setActive(true);
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());


        supplierRepository.save(supplier);
        result.setMessage("successfully edited");
        result.setSuccess(true);
        return result;
    }

    public Result active(Long id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()){
            result.setMessage("this supplier not found");
            result.setSuccess(false);
            return result;
        }
       Supplier supplier = optionalSupplier.get();

        if (supplier.isActive()) {
            result.setMessage("this supplier active");
            result.setSuccess(false);
            return result;
        }
        supplier.setActive(true);

        supplierRepository.save(supplier);
        result.setMessage("succesfully actived");
        result.setSuccess(true);
        return result;
    }

    public Result delete(Long id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()){
            result.setMessage("this supplier not found");
            result.setSuccess(false);
            return result;
        }
        Supplier supplier= optionalSupplier.get();
        if (!supplier.isActive()) {
            result.setMessage("this supplier no active");
            result.setSuccess(false);
            return result;
        }
       supplier.setActive(false);
        supplierRepository.save(supplier);
        result.setMessage("succesfully deleted");
        result.setSuccess(true);
        return result;
    }

    public Supplier getById(Long id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (!optionalSupplier.isPresent()){
            return null;
        }
        return optionalSupplier.get();

    }

    public Page<Supplier> getAll(int page){
        Pageable pageable= PageRequest.of(page,10);
        return supplierRepository.findAll(pageable);
    }


}
