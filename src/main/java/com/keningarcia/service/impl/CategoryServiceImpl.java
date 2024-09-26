package com.keningarcia.service.impl;

import com.keningarcia.model.Category;
import com.keningarcia.repository.ICategoryRepo;
import com.keningarcia.repository.IGenericRepo;
import com.keningarcia.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    private final ICategoryRepo repo;

    @Override
    protected IGenericRepo getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByNameService(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Category> getNameAndDescription1(String name, String description) {
        return repo.getNameAndDescription1(name, description);
    }

    @Override
    public Page<Category> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Category> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction, "name"));
    }

    /*
    @Override
    public Category save(Category category) throws Exception {
        return repo.save(category);
    }

    @Override
    public Category update(Category category, Integer id) throws Exception {
        //VALIDACION MAS ADELANTE
        category.setIdCategory(id);
        return repo.save(category);
    }

    @Override
    public List<Category> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Category readById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Category());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    } */

    /*public Category validateCategory(Category category) {
        if(category.getIdCategory() != 0){
            return repo.getCategoryX(category);
        }else{
            return new Category();
        }
    }*/
}
