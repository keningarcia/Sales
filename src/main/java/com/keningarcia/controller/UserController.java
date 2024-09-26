package com.keningarcia.controller;

import com.keningarcia.dto.GenericResponse;
import com.keningarcia.dto.UserDTO;
import com.keningarcia.model.User;
import com.keningarcia.service.IUserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll() throws Exception{
        List<UserDTO> list = service.readAll().stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception{
        User obj = service.readById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<UserDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        User obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse(200, "succes", Arrays.asList(convertToDTO(obj))));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
        //return ResponseEntity.created(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto, @PathVariable("id") Integer id) throws Exception{
        User obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private UserDTO convertToDTO(User obj){
        return modelMapper.map(obj, UserDTO.class);
    }

    private User convertToEntity(UserDTO dto){
        return modelMapper.map(dto, User.class);
    }

    /*public User getControllerSimple(){

        return service.validateUser(new User(1,"tv","television",true));
    }*/
}
