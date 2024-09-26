package com.keningarcia.service;

import com.keningarcia.dto.IProcedureDTO;
import com.keningarcia.dto.ProcedureDTO;
import com.keningarcia.model.Sale;

import java.util.List;
import java.util.Map;


public interface ISaleService extends ICRUD<Sale, Integer>{

    List<ProcedureDTO> callProcedure1();
    List<IProcedureDTO> callProcedure2();
    //List<ProcedureDTO> callProcedure3();
    void callProcedure4();
    Sale getSaleMostExpensive(); //obtener la venta mayor
    String getBestSellerPerson(); //obtener el nombre del mejor vendedor
    Map<String, Long> getSalesCountBySeller(); //contar cantidad de ventas por vendedor
    Map<String, Double> getMostSellerProduct(); //El producto mas vendido


}
