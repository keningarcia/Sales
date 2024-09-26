package com.keningarcia.service;

import com.keningarcia.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICategoryService extends ICRUD<Category, Integer> {

    List<Category> findByNameService(String name);

    List<Category> getNameAndDescription1(String name, String description);

    Page<Category> findPage(Pageable pageable);

    List<Category> findAllOrder(String param);

}
