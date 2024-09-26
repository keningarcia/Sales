package com.keningarcia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {

    private Integer idCategory;
    @NotNull
    @Size(min = 1, max = 30)
    private String nameofCategory;
    @NotNull
    private String descriptionCategory;
    @NotNull
    private boolean enabledCategory;
}
