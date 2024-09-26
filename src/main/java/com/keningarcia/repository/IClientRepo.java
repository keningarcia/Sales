package com.keningarcia.repository;

import com.keningarcia.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepo extends IGenericRepo<Client, Integer> {
}
