package com.github.yuta600.kondateai.controller;

import com.github.yuta600.kondateai.dto.request.MenuRequest;
import com.github.yuta600.kondateai.dto.response.MenuResponse;
import com.github.yuta600.kondateai.exception.BadRequestException;
import com.github.yuta600.kondateai.service.AiService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final AiService aiService;

    public MenuController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public MenuResponse generateMenu(@RequestBody @Valid MenuRequest request) {
        if (!request.isValidFamilySize()) {
            throw new BadRequestException("大人と子供は合わせて1人以上になるようにしてください。");
        }
        return aiService.getMenuFromAi(request);
    }

}
