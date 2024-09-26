package com.keningarcia.service.impl;

import com.keningarcia.model.Client;
import com.keningarcia.repository.IGenericRepo;
import com.keningarcia.repository.IClientRepo;
import com.keningarcia.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService{

    private final IClientRepo repo;

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }




    /*public Client validateClient(Client client) {
        if(client.getIdClient() != 0){
            return repo.getClientX(client);
        }else{
            return new Client(g);
        }
    }*/
}
