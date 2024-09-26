package com.keningarcia.controller;

import com.keningarcia.dto.GenericResponse;
import com.keningarcia.dto.IProcedureDTO;
import com.keningarcia.dto.ProcedureDTO;
import com.keningarcia.dto.SaleDTO;
import com.keningarcia.model.Sale;
import com.keningarcia.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final ISaleService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception{
        List<SaleDTO> list = service.readAll().stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<SaleDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse(200, "succes", Arrays.asList(convertToDTO(obj))));
    }

    @GetMapping("/resume")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume(){
        return new ResponseEntity<>(service.callProcedure1(), HttpStatus.OK);
    }

    @GetMapping("/resume2")
    public ResponseEntity<List<IProcedureDTO>> getSaleResume2(){
        return new ResponseEntity<>(service.callProcedure2(), HttpStatus.OK);
    }

    /*@GetMapping("/resume3")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume3(){
        return new ResponseEntity<>(service.callProcedure3(), HttpStatus.OK);
    }*/

    @GetMapping("/resume4")
    public ResponseEntity<Void> getSaleResume4(){
        service.callProcedure4();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/mostexpensive")
    public ResponseEntity<SaleDTO> getMostExpensive(){
        SaleDTO dto = convertToDTO(service.getSaleMostExpensive());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/bestseller")
    public ResponseEntity<String> getBestSeller(){
        String user = service.getBestSellerPerson();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/sellercount")
    public ResponseEntity<Map<String, Long>> getSellerCount(){
        Map<String, Long> byUser = service.getSalesCountBySeller();
        return new ResponseEntity<>(byUser, HttpStatus.OK);
    }

    @GetMapping("/bestproduct")
    public ResponseEntity<Map<String, Double>> getBestProduct(){
        Map<String, Double> byUser = service.getMostSellerProduct();
        return new ResponseEntity<>(byUser, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
        //return ResponseEntity.created(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO dto, @PathVariable("id") Integer id) throws Exception{
        Sale obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private SaleDTO convertToDTO(Sale obj){
        return modelMapper.map(obj, SaleDTO.class);
    }

    private Sale convertToEntity(SaleDTO dto){
        return modelMapper.map(dto, Sale.class);
    }

    /*public Sale getControllerSimple(){

        return service.validateSale(new Sale(1,"tv","television",true));
    }*/
}
