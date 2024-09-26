package com.keningarcia.repository;

import com.keningarcia.model.User;

public interface IUserRepo extends IGenericRepo<User, Integer> {
    //@Query("FROM User u WHERE u.username = :username")
    User findOneByUsername(String username);
}
