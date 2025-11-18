package com.github.yuta600.kondateai.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MenuRequest {
    @Min(0)
    @Max(10)
    private int adults;

    @Min(0)
    @Max(10)
    private int children;

    @Min(0)
    private double budget;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String supermarketName;

    public boolean isValidFamilySize () {
        return adults + children >= 1;
    }
}
