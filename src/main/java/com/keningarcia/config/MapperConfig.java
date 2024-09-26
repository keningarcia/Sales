package com.keningarcia.config;

import com.keningarcia.dto.CategoryDTO;
import com.keningarcia.dto.ProductDTO;
import com.keningarcia.model.Category;
import com.keningarcia.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("categoryMapper")
    public ModelMapper categoryMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //lectura
        TypeMap<Category, CategoryDTO> typeMap1= modelMapper.createTypeMap(Category.class, CategoryDTO.class);
        typeMap1.addMapping(Category::getName, (dest, v) -> dest.setNameofCategory((String) v));

        //escritura
        TypeMap<CategoryDTO, Category> typeMap2= modelMapper.createTypeMap(CategoryDTO.class, Category.class);
        typeMap2.addMapping(CategoryDTO::getNameofCategory, (dest, v) -> dest.setName((String) v));

        return modelMapper;
    }

    @Bean("productMapper")
    public ModelMapper productMapper() {
        ModelMapper mapper = new ModelMapper();
        /*
        //lectura
        TypeMap<Product, ProductDTO> typeMap1= modelMapper.createTypeMap(Product.class, ProductDTO.class);
        typeMap1.addMapping(e -> e.getCategory(), (dest, v) -> dest.setIdCategory((Integer) v));

        //escritura
        TypeMap<ProductDTO, Product> typeMap2= modelMapper.createTypeMap(ProductDTO.class, Product.class);
        typeMap2.addMapping(ProductDTO::getIdCategory, (dest, v) -> dest.getCategory().setIdCategory((Integer) v));
        */
        return mapper;
    }

    @Bean("defaultMapper")
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }
}
