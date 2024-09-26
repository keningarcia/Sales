package com.keningarcia.controller;

import com.keningarcia.dto.ProductDTO;
import com.keningarcia.dto.GenericResponse;
import com.keningarcia.model.Product;
import com.keningarcia.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService service;
    @Qualifier("productMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        List<ProductDTO> list = service.readAll().stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ProductDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse(200, "succes", Arrays.asList(convertToDTO(obj))));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
        //return ResponseEntity.created(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto, @PathVariable("id") Integer id) throws Exception{
        Product obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ProductDTO convertToDTO(Product obj){
        return modelMapper.map(obj, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO dto){
        return modelMapper.map(dto, Product.class);
    }

    /*public Product getControllerSimple(){

        return service.validateProduct(new Product(1,"tv","television",true));
    }*/
}
