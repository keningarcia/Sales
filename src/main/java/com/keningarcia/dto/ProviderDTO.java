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
public class ProviderDTO {
    private Integer idProvider;

    @NotNull
    @Size(min = 3)
    private String nameProvider;

    @NotNull
    @Size(min = 3)
    private String addressProvider;

    @NotNull
    private boolean enabledProvider;

}
