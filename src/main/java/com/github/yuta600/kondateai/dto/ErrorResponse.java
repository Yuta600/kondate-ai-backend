package com.github.yuta600.kondateai.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private boolean success;
    private String message;
}
