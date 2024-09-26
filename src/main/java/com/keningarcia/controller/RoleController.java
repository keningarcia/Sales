package com.keningarcia.controller;

import com.keningarcia.dto.GenericResponse;
import com.keningarcia.dto.RoleDTO;
import com.keningarcia.model.Role;
import com.keningarcia.service.IRoleService;
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
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> readAll() throws Exception{
        List<RoleDTO> list = service.readAll().stream().map(this::convertToDTO).toList();

        return ResponseEntity.ok(list);
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Role obj = service.readById(id);

        return ResponseEntity.ok(convertToDTO(obj));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<RoleDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Role obj = service.readById(id);

        return ResponseEntity.ok(new GenericResponse(200, "succes", Arrays.asList(convertToDTO(obj))));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO dto) throws Exception{
        Role obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDTO(obj), HttpStatus.CREATED);
        //return ResponseEntity.created(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO dto, @PathVariable("id") Integer id) throws Exception{
        Role obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    private RoleDTO convertToDTO(Role obj){
        return modelMapper.map(obj, RoleDTO.class);
    }

    private Role convertToEntity(RoleDTO dto){
        return modelMapper.map(dto, Role.class);
    }

    /*public Role getControllerSimple(){

        return service.validateRole(new Role(1,"tv","television",true));
    }*/
}
