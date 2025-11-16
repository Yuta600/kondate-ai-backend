package com.github.yuta600.kondateai.controller;

import com.github.yuta600.kondateai.dto.MenuRequest;
import com.github.yuta600.kondateai.exception.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @PostMapping("/generate")
    public String generateMenu(@RequestBody @Valid MenuRequest request) {
        if (!request.isValidFamilySize()) {
            throw new BadRequestException("大人と子供は合わせて1人以上になるようにしてください。");
        }
        return request.getPostalCode();
    }
}
