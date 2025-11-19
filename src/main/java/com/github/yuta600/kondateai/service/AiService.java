package com.github.yuta600.kondateai.service;

import com.github.yuta600.kondateai.dto.request.MenuRequest;
import com.github.yuta600.kondateai.dto.response.MenuResponse;

public interface AiService {
    MenuResponse getMenuFromAi(MenuRequest request);
}
