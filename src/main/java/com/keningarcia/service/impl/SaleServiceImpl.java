package com.keningarcia.service.impl;

import com.keningarcia.dto.IProcedureDTO;
import com.keningarcia.dto.ProcedureDTO;
import com.keningarcia.model.Sale;
import com.keningarcia.model.SaleDetail;
import com.keningarcia.repository.IGenericRepo;
import com.keningarcia.repository.ISaleRepo;
import com.keningarcia.service.ISaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService{

    private final ISaleRepo repo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<ProcedureDTO> callProcedure1() {
        List<ProcedureDTO> list = new ArrayList<>();
        repo.callProcedure1().forEach(e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityfn((Integer) e[0]);
            dto.setDatetimefn((String) e[1]);
            list.add(dto);
        });

        return list;
    }

    @Override
    public List<IProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    /*@Override
    public List<ProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }*/

    @Override
    public void callProcedure4() {
        repo.callProcedure4();
    }

    @Override
    public Sale getSaleMostExpensive() {
        return repo.findAll()
                .stream()
                .max(Comparator.comparing(Sale::getTotal))
                .orElse(new Sale());
    }

    @Override
    public String getBestSellerPerson() {
        Map<String, Double> byUser = repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername(), Collectors.summingDouble(Sale::getTotal)));
        //System.out.println(byUser);

        return Collections.max(byUser.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
    }

    @Override
    public Map<String, Long> getSalesCountBySeller() {
        Map<String, Long> byUser = repo.findAll()
                .stream()
                .collect(groupingBy(s -> s.getUser().getUsername(), Collectors.counting()));

        return byUser;
    }

    @Override
    public Map<String, Double> getMostSellerProduct() {
        Stream<Sale> saleStream = repo.findAll().stream();
        Stream<List<SaleDetail>> listStream = saleStream.map(Sale::getDetails);

        Stream<SaleDetail> streamDetail = listStream.flatMap(Collection::stream);
        Map<String, Double> byProduct = streamDetail
                .collect(groupingBy(d -> d.getProduct().getName(), summingDouble(SaleDetail::getQuantity)));

    return byProduct.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new
            ));
    }




    /*public Sale validateSale(Sale sale) {
        if(sale.getIdSale() != 0){
            return repo.getSaleX(sale);
        }else{
            return new Sale(g);
        }
    }*/
}
