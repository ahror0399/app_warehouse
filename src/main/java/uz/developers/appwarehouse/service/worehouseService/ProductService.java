package uz.developers.appwarehouse.service.worehouseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.ProductDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Attechment;
import uz.developers.appwarehouse.entity.warehouseEntity.Category;
import uz.developers.appwarehouse.entity.warehouseEntity.Measurement;
import uz.developers.appwarehouse.entity.warehouseEntity.Product;
import uz.developers.appwarehouse.repository.warehouse.AttechmentRepository;
import uz.developers.appwarehouse.repository.warehouse.CategoryRepository;
import uz.developers.appwarehouse.repository.warehouse.MeasurementRepository;
import uz.developers.appwarehouse.repository.warehouse.ProductRepository;
import uz.developers.appwarehouse.results.CodeGeneratorUUID;
import uz.developers.appwarehouse.results.Result;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttechmentRepository attechmentRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    CodeGeneratorUUID codeGeneratorUUID;

    @Autowired
    Result result;
    public Result  add(ProductDto productDto){
        Long cat_id=productDto.getCategory_id().longValue();

        boolean b = productRepository.existsByNameAndCategoryId(productDto.getName(), cat_id);
        if (b){
            result.setMessage("Sorry.This product has ben saved");
            result.setSuccess(false);
            return result;
        }


        Optional<Category> optionalCategory = categoryRepository.findById(cat_id);
        if (!optionalCategory.isPresent()){
            result.setMessage("category not found");
            result.setSuccess(false);
            return result;
        }

        List<Long> longList = new ArrayList<>();

        List<Integer> it = productDto.getAttechment_id();
        for (Integer integer : it) {
            longList.add(Integer.toUnsignedLong(integer));
        }


        List<Attechment> attechments = attechmentRepository.findAllById(longList);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurement_id().longValue());
        if (!optionalMeasurement.isPresent()){
            result.setMessage("measurement not found");
            result.setSuccess(false);
            return result;
        }
        Product product=new Product();
        product.setAttechments(attechments);
        product.setCategory(optionalCategory.get());
        product.setCode(codeGeneratorUUID.getCode());
        product.setMeasurement(optionalMeasurement.get());
        product.setActive(true);
        product.setName(productDto.getName());
        productRepository.save(product);
        result.setMessage("product successfully saved");
        result.setSuccess(true);
        return result;


    }

    public Result edit(Long id, ProductDto productDto){

        Long id1=id.longValue();
        Optional<Product> optionalProduct = productRepository.findById(id1);

        if (!optionalProduct.isPresent()){
            result.setMessage("product not found");
            result.setSuccess(false);
            return result;
        }

        if (!optionalProduct.get().isActive()) {
            result.setMessage("Sorry! Product no avtived");
            result.setSuccess(false);
            return result;
        }
        Long cat_id=productDto.getCategory_id().longValue();

        Optional<Category> optionalCategory = categoryRepository.findById(cat_id);
        if (!optionalCategory.isPresent()){
            result.setMessage("category not found");
            result.setSuccess(false);
            return result;
        }
        List<Long> longList = new ArrayList<>();

        List<Integer> it = productDto.getAttechment_id();
        for (Integer integer : it) {
            longList.add(Integer.toUnsignedLong(integer));
        }


        List<Attechment> attechments = attechmentRepository.findAllById(longList);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurement_id().longValue());
        if (!optionalMeasurement.isPresent()){
            result.setMessage("measurement not found");
            result.setSuccess(false);
            return result;
        }
        Product product=optionalProduct.get();
        product.setAttechments(attechments);
        product.setCategory(optionalCategory.get());
        product.setCode(codeGeneratorUUID.getCode());
        product.setMeasurement(optionalMeasurement.get());
        product.setActive(true);
        product.setName(productDto.getName());
        productRepository.save(product);
        result.setMessage("product successfully edited");
        result.setSuccess(true);
        return result;


    }

    public Result noActive(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()){
            result.setMessage("product not found");
            result.setSuccess(false);
            return result;
        }
        if (!optionalProduct.get().isActive()) {
            result.setMessage(" Product no avtived");
            result.setSuccess(false);
            return result;
        }
        Product product= optionalProduct.get();
        product.setActive(false);
        productRepository.save(product);
        result.setMessage("product successfully no actived");
        result.setSuccess(true);
        return result;
    }

    public Result active(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()){
            result.setMessage("product not found");
            result.setSuccess(false);
            return result;
        }
        if (optionalProduct.get().isActive()) {
            result.setMessage(" Product has ben avtived");
            result.setSuccess(false);
            return result;
        }
        Product product= optionalProduct.get();
        product.setActive(true);
        productRepository.save(product);
        result.setMessage("product successfully actived");
        result.setSuccess(true);
        return result;
    }

    public Page<Product> getByCategoryId(Long id, int page){
                Pageable pageable= PageRequest.of(page,10);
        return productRepository.findAllByCategoryIdAndActiveIsTrue(id, pageable);

    }

    public Product getById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);

    }


}
