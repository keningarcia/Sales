package com.keningarcia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Integer idProduct;
    @NotNull
    @Min(value = 1)
    private Integer idCategory;
    @NotNull
    private String nameProduct;
    @NotNull
    private String descriptionProduct;
    @Min(value = 1)
    private double priceProduct;
    @NotNull
    private boolean enabledProduct;
}
