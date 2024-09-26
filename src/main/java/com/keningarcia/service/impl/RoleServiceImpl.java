package com.keningarcia.service.impl;

import com.keningarcia.model.Role;
import com.keningarcia.repository.IGenericRepo;
import com.keningarcia.repository.IRoleRepo;
import com.keningarcia.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService{

    private final IRoleRepo repo;

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }




    /*public Role validateRole(Role role) {
        if(role.getIdRole() != 0){
            return repo.getRoleX(role);
        }else{
            return new Role(g);
        }
    }*/
}
