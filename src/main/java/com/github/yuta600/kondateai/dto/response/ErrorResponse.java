package com.github.yuta600.kondateai.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private boolean success;
    private String message;
}
