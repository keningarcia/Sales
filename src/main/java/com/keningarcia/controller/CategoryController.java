package com.keningarcia.controller;

import com.keningarcia.dto.CategoryDTO;
import com.keningarcia.dto.GenericResponse;
import com.keningarcia.model.Category;


import com.keningarcia.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService service;
    @Qualifier("categoryMapper")
    private final ModelMapper modelMapper;

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("@authServiceImpl.hasAccess('readAll')")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        List<CategoryDTO> list = service.readAll().stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CategoryDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse(200, "succes", Arrays.asList(convertToDTO(obj))));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<CategoryDTO>> save(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.save(convertToEntity(dto));

        return ResponseEntity.created(URI.create("/categories/"+obj.getIdCategory())).body(new GenericResponse<>(201,"success", Arrays.asList(convertToDTO(obj))));
        //return ResponseEntity.created(URI.create("/categories/"+obj.getIdCategory())).body(new GenericResponse<>(201, "success", Arrays.asList(convertToDTO(obj))));
        //return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
        //return ResponseEntity.created(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto, @PathVariable("id") Integer id) throws Exception{
        //dto.setIdCategory(id);
        Category obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
    ///QUERY///
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName (@PathVariable("name") String name){
        List<CategoryDTO> list = service.findByNameService(name).stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/name/description")
    public ResponseEntity<List<CategoryDTO>> getNameAndDescription (@RequestParam("name") String name, @RequestParam("description") String description){
        List<CategoryDTO> list = service.getNameAndDescription1(name, description).stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<CategoryDTO>> findPage(Pageable pageable){
        Page<CategoryDTO> pageCategoryDTO = service.findPage(pageable).map(this::convertToDTO);
        return ResponseEntity.ok(pageCategoryDTO);
    }

    @GetMapping("/pagination2")
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(name = "p", defaultValue = "0") int page,
            @RequestParam(name = "s", defaultValue = "3") int size
    ){
        Page<CategoryDTO> pageCategoryDTO = service.findPage(PageRequest.of(page,size)).map(this::convertToDTO);
        return ResponseEntity.ok(pageCategoryDTO);
    }

    @GetMapping("/order")
    public ResponseEntity<List<CategoryDTO>> findAllOrder(@RequestParam(name = "param", defaultValue = "ASC") String param){
        List<CategoryDTO> list = service.findAllOrder(param).stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
    }

    private CategoryDTO convertToDTO(Category obj){
        return modelMapper.map(obj, CategoryDTO.class);
    }

    private Category convertToEntity(CategoryDTO dto){
        return modelMapper.map(dto, Category.class);
    }

    /*public Category getControllerSimple(){

        return service.validateCategory(new Category(1,"tv","television",true));
    }*/
}
