package com.keningarcia.repository;

import com.keningarcia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends IGenericRepo<Product, Integer> {
}
