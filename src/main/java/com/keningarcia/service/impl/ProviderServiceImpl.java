package com.keningarcia.service.impl;

import com.keningarcia.model.Provider;
import com.keningarcia.repository.IGenericRepo;
import com.keningarcia.repository.IProviderRepo;
import com.keningarcia.service.IProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService{

    private final IProviderRepo repo;

    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return repo;
    }




    /*public Provider validateProvider(Provider provider) {
        if(provider.getIdProvider() != 0){
            return repo.getProviderX(provider);
        }else{
            return new Provider(g);
        }
    }*/
}
