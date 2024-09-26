package com.keningarcia.controller;

import com.keningarcia.dto.GenericResponse;
import com.keningarcia.dto.ProviderDTO;
import com.keningarcia.model.Provider;
import com.keningarcia.service.IProviderService;
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
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final IProviderService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception{
        List<ProviderDTO> list = service.readAll().stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Provider obj = service.readById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ProviderDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Provider obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse(200, "succes", Arrays.asList(convertToDTO(obj))));
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> save(@Valid @RequestBody ProviderDTO dto) throws Exception{
        Provider obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
        //return ResponseEntity.created(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO dto, @PathVariable("id") Integer id) throws Exception{
        Provider obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ProviderDTO convertToDTO(Provider obj){
        return modelMapper.map(obj, ProviderDTO.class);
    }

    private Provider convertToEntity(ProviderDTO dto){
        return modelMapper.map(dto, Provider.class);
    }

    /*public Provider getControllerSimple(){

        return service.validateProvider(new Provider(1,"tv","television",true));
    }*/
}
