package uz.developers.appwarehouse.service.worehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developers.appwarehouse.dto.CategoryDto;
import uz.developers.appwarehouse.entity.warehouseEntity.Category;
import uz.developers.appwarehouse.repository.warehouse.CategoryRepository;
import uz.developers.appwarehouse.results.Result;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    Result result;

    public Result add(CategoryDto categoryDto){
        if (categoryDto.getCategory_id()==null){


            boolean byName = categoryRepository.existsByName(categoryDto.getName());
            if (byName){
                result.setSuccess(false);

                result.setMessage("Sorry this present category has ben saved");
                return result;
            }

            Category category=new Category();
            category.setActive(true);
            category.setName(categoryDto.getName());
            category.setCategory(null);
            categoryRepository.save(category);
            result.setSuccess(true);

            result.setMessage("successfully saved present category");
            return result;
        }

        boolean byName = categoryRepository.existsByNameAndCategoryId(categoryDto.getName(), categoryDto.getCategory_id());
        if (byName){
            result.setSuccess(false);

            result.setMessage("Sorry this category has ben saved");
            return result;
        }

        Optional<Category> presentCategory = categoryRepository.findById(categoryDto.getCategory_id());


        Category category=new Category();
        category.setActive(true);
        category.setName(categoryDto.getName());
        category.setCategory(presentCategory.get());
        categoryRepository.save(category);

        result.setSuccess(true);
        result.setMessage("successfully saved category");
        return result;

    }

    public Result delete(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            result.setSuccess(false);
            result.setMessage("category not found");
            return result;
        }
        Category category = optionalCategory.get();
        category.setActive(false);
        categoryRepository.save(category);
        result.setSuccess(true);
        result.setMessage("successfully no actived category");
        return result;

    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public List<Category> getByPresentId(Long id){
        return categoryRepository.findAllByCategoryIdAndActiveIsTrue(id);
    }

    public Result active(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            result.setSuccess(false);
            result.setMessage("category not found");
            return result;
        }
        Category category = optionalCategory.get();
        category.setActive(true);
        categoryRepository.save(category);
        result.setSuccess(true);
        result.setMessage("successfully  actived category");
        return result;

    }

    public Result edit(CategoryDto categoryDto,Long id){

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            result.setSuccess(false);
            result.setMessage("category not found");
            return result;
        }

        if (categoryDto.getCategory_id()==null){

            Category category= optionalCategory.get();

            category.setName(categoryDto.getName());
            category.setCategory(null);
            categoryRepository.save(category);
            result.setSuccess(true);

            result.setMessage("successfully edited present category");
            return result;
        }
        Optional<Category> presentCategory = categoryRepository.findById(categoryDto.getCategory_id());


        Category category=optionalCategory.get();
        category.setActive(true);
        category.setName(categoryDto.getName());
        category.setCategory(presentCategory.get());
        categoryRepository.save(category);

        result.setSuccess(true);
        result.setMessage("successfully edited category");
        return result;

    }



}
