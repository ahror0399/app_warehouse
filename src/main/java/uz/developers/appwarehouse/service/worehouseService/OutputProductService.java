package uz.developers.appwarehouse.service.worehouseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uz.developers.appwarehouse.dto.OutputProductDto;
import uz.developers.appwarehouse.entity.warehouseEntity.*;
import uz.developers.appwarehouse.repository.warehouse.*;
import uz.developers.appwarehouse.results.Result;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

   @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Result result;

    @Autowired
    AllProductRepository allProductRepository;

    public Result add(OutputProductDto outputProductDto,int day){

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProduct_id());
        if (!optionalProduct.isPresent()){
            result.setMessage("product not found");
            result.setSuccess(false);
            return result;
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutput_id());
        if (!optionalOutput.isPresent()){
            result.setMessage("Output not found");
            result.setSuccess(false);
            return result;
        }

        //Bu yerdaa so'ralgan mahsulotning yaroqlilik muddati so'ralgan kundan yani
        // bergan kundan ko'p  bo'lgan  mahsulotlarni olamiz.Bu uchun pastda method bor
        List<AllProduct> allProducts=getAllByProductIdBeetweeenExpireDateN(day, outputProductDto.getProduct_id());

        double count=0, needAmount=outputProductDto.getAmount();
        for (AllProduct allProduct : allProducts) {
            count=count + allProduct.getAmount();

        }

        //agar shu mahsulot soni so'ralgandan kam bo'lsa
        if (count< needAmount){
            result.setMessage("the amount is not enough of products");
            result.setSuccess(false);
            return result;

        }


        //Endi so'ralgan mahsulotni  Barcha mahsulotlar jadvalidan kamaytiramiz.
        // Buning uchun tekshiramiz. Bu yerdaa yaroqlilik muddati ham borligi uchun so'ralgan mahsulotning soni
        //yaroqlilik muddatiga qarab har xil bo'ladi

        for (AllProduct allProduct : allProducts) {

            if (allProduct.getAmount()>=needAmount){
                AllProduct allProduct1=allProduct;
                allProduct1.setAmount(allProduct.getAmount()-outputProductDto.getAmount());
                allProductRepository.save(allProduct1);
                break;
            }else {
                needAmount=needAmount-allProduct.getAmount();
                AllProduct allProduct1 = allProduct;
                allProduct1.setAmount(0d);
                allProductRepository.save(allProduct1);

            }

        }
        Output_Product output_product=new Output_Product();
        output_product.setProduct(output_product.getProduct());
        output_product.setOutput(optionalOutput.get());
        output_product.setPrice(outputProductDto.getPrice());
        output_product.setAmount(outputProductDto.getAmount());
        outputProductRepository.save(output_product);

        result.setMessage("successfully outputed");
        result.setSuccess(true);
        return result;



    }



    public Page<Output_Product> getByProductId(Long id,int page){
        Pageable pageable= PageRequest.of(page,10);
        return outputProductRepository.findAllByProductId(id, pageable);
    }



    public Page<Output_Product> getOutputProductByCategoryIdOrPriceBeetwen(
            int page,double price1, double price2,Long category_id
    ){
        Optional<Category> byId = categoryRepository.findById(category_id);
        Pageable pageable= PageRequest.of(page,10);
        return outputProductRepository.findAllByProduct_CategoryOrPriceBetween(byId.get(), price1,
                price2,pageable);
    }



    public List<?> getOutputProductForOneDay( LocalDate  day1,int page){
      Pageable pageable= PageRequest.of(page,10);
        return outputProductRepository.getinputProductForDay(day1,pageable);
    }





    public List<AllProduct> getAllByProductIdBeetweeenExpireDateN(int day, Long id){
        return allProductRepository.getByProductIdAndExpireDateBetweenNow(day,id);
    }









}
