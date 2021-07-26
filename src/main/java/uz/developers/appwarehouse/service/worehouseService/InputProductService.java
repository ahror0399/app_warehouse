package uz.developers.appwarehouse.service.worehouseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.InputProductDto;
import uz.developers.appwarehouse.entity.warehouseEntity.*;
import uz.developers.appwarehouse.repository.warehouse.*;
import uz.developers.appwarehouse.results.Result;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

   @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AllProductRepository allProductRepository;

    @Autowired
    Result result;

    public Result add(InputProductDto inputProductDto){

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProduct_id());
        if (!optionalProduct.isPresent()){
            result.setMessage("product not found");
            result.setSuccess(false);
            return result;
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInput_id());
        if (!optionalInput.isPresent()){
            result.setMessage("Input not found");
            result.setSuccess(false);
            return result;
        }
        Input_Product input_product=new Input_Product();
        input_product.setProduct(optionalProduct.get());
        input_product.setInput(optionalInput.get());
        input_product.setAmount(inputProductDto.getAmount());
        input_product.setPrice(inputProductDto.getPrice());
        input_product.setExpireDate(inputProductDto.getExpire_date());

        Optional<AllProduct> optionalAllProduct = allProductRepository.findByProductIdAndExpire_date(inputProductDto.getProduct_id(), inputProductDto.getExpire_date());

        if (!optionalAllProduct.isPresent()){
            AllProduct allProduct=new AllProduct();
            allProduct.setProduct(optionalProduct.get());
            allProduct.setActive(true);
            allProduct.setExpire_date(inputProductDto.getExpire_date());
            allProduct.setAmount(inputProductDto.getAmount());
            allProductRepository.save(allProduct);
        }else {
            AllProduct allProduct1= optionalAllProduct.get();
            allProduct1.setProduct(optionalProduct.get());
            allProduct1.setActive(true);
            allProduct1.setExpire_date(inputProductDto.getExpire_date());
            allProduct1.setAmount(inputProductDto.getAmount()+allProduct1.getAmount());
            allProductRepository.save(allProduct1);
        }


        inputProductRepository.save(input_product);
        result.setMessage("successfully saved");
        result.setSuccess(true);
        return result;



    }

    public Result edit(Long id, InputProductDto inputProductDto){
        Optional<Input_Product> optionalInput_product = inputProductRepository.findById(id);
        if (!optionalInput_product.isPresent()){
            result.setMessage("Input product not found");
            result.setSuccess(false);
            return result;
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProduct_id());
        if (!optionalProduct.isPresent()){
            result.setMessage("product not found");
            result.setSuccess(false);
            return result;
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInput_id());
        if (!optionalInput.isPresent()){
            result.setMessage("Input not found");
            result.setSuccess(false);
            return result;
        }


        Input_Product input_product=optionalInput_product.get();
        input_product.setProduct(optionalProduct.get());
        input_product.setInput(optionalInput.get());
        input_product.setAmount(inputProductDto.getAmount());
        input_product.setPrice(inputProductDto.getPrice());
        input_product.setExpireDate(inputProductDto.getExpire_date());

        inputProductRepository.save(input_product);
        result.setMessage("successfully edited");
        result.setSuccess(true);
        return result;


    }

    public Page<Input_Product> getByProductId(Long id,int page){
        Pageable pageable= PageRequest.of(page,10);
        return inputProductRepository.findAllByProductId(id, pageable);
    }


    public Page<Input_Product> getByExpireDateLifetime(int page, int day1){
        Pageable pageable= PageRequest.of(page,10);
        return inputProductRepository.findAllByExpireDate( pageable, day1);
    }


    public Page<Input_Product> getInputProductByCategoryIdOrPriceBeetwen(
            int page,double price1, double price2,Long category_id
    ){
        Optional<Category> byId = categoryRepository.findById(category_id);
        Pageable pageable= PageRequest.of(page,10);
        return inputProductRepository.findAllByProduct_CategoryOrPriceBetween(byId.get(), price1,
                price2,pageable);
    }
    public List<?> getInputProductForOneDay( LocalDate  day1,int page){
      Pageable pageable= PageRequest.of(page,10);
        return inputProductRepository.getinputProductForDay(day1,pageable);
    }











}
