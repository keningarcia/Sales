package com.keningarcia.service.impl;

import com.keningarcia.model.User;
import com.keningarcia.repository.IGenericRepo;
import com.keningarcia.repository.IUserRepo;
import com.keningarcia.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService{

    private final IUserRepo repo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }




    /*public User validateUser(User user) {
        if(user.getIdUser() != 0){
            return repo.getUserX(user);
        }else{
            return new User(g);
        }
    }*/
}
