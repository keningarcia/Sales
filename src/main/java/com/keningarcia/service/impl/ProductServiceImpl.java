package com.keningarcia.service.impl;

import com.keningarcia.model.Product;
import com.keningarcia.repository.IGenericRepo;
import com.keningarcia.repository.IProductRepo;
import com.keningarcia.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService{

    private final IProductRepo repo;

    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }




    /*public Product validateProduct(Product product) {
        if(product.getIdProduct() != 0){
            return repo.getProductX(product);
        }else{
            return new Product(g);
        }
    }*/
}
