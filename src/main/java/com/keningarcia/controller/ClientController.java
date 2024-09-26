package com.keningarcia.controller;

import com.keningarcia.dto.GenericResponse;
import com.keningarcia.dto.ClientDTO;
import com.keningarcia.model.Client;
import com.keningarcia.service.IClientService;
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
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception{
        List<ClientDTO> list = service.readAll().stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ClientDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse(200, "succes", Arrays.asList(convertToDTO(obj))));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO dto) throws Exception{
        Client obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
        //return ResponseEntity.created(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto, @PathVariable("id") Integer id) throws Exception{
        Client obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ClientDTO convertToDTO(Client obj){
        return modelMapper.map(obj, ClientDTO.class);
    }

    private Client convertToEntity(ClientDTO dto){
        return modelMapper.map(dto, Client.class);
    }

    /*public Client getControllerSimple(){

        return service.validateClient(new Client(1,"tv","television",true));
    }*/
}
