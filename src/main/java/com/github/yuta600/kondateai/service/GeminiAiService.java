package com.github.yuta600.kondateai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yuta600.kondateai.dto.request.MenuRequest;
import com.github.yuta600.kondateai.dto.response.MenuResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@Primary
public class GeminiAiService implements AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public GeminiAiService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public MenuResponse getMenuFromAi(MenuRequest request) {
        String prompt = createPrompt(request);

        Map<String, Object> requestsBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );
        String response = webClient.post()
                .uri(apiUrl)
                .header("Content-Type", "application/json")
                .header("x-goog-api-key", apiKey)
                .bodyValue(requestsBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return parseResponse(response);
    }

    private String createPrompt(MenuRequest request) {
        int budgetInYen = (int) (request.getBudget() * 10000);

        return String.format("""
            以下の条件で三日分の献立を提案してください。
            
            【条件】
            - 大人: %d人
            - 子供: %d人
            - 予算: %d円
            - 地域: %s
            - スーパー: %s
            
            【出力形式】
            以下のJSON形式でのみ返してください。
            マークダウンのコードブロック記法(```)は使用しないでください。
            JSONオブジェクトのみを返してください。
            説明文や前置きは一切不要です。
            
            {
                "menus": [
                    {
                         "day": "月曜日",
                         "title": "献立名",
                         "ingredients" : [
                            {"name": "材料名", "quantity": "分量"}
                         ],
                         "cost": 1000
                    }
                ],
                "shoppingList": [
                    "鶏もも肉 2枚 (約500g)",
                    "玉ねぎ 1個",
                    "醤油 大さじ3"
                ],
                "totalCost": 3000
            }
            
            ※ shoppingListには必ず「材料名 + 分量」の形式で記載してください
            """,
                request.getAdults(),
                request.getChildren(),
                budgetInYen,
                request.getPostalCode(),
                request.getSupermarketName()
        );
    }

    private MenuResponse parseResponse(String response) {
        try {
            System.out.println(response);
            JsonNode root = objectMapper.readTree(response);
            String text = root.path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();

            text = text.trim();
            if (text.startsWith("'''json")) {
                text = text.substring(7);
            }
            if (text.startsWith("'''")) {
                text = text.substring(3);
            }
            if (text.endsWith("'''")) {
                text = text.substring(0, text.length() - 3);
            }
            text = text.trim();

            return objectMapper.readValue(text, MenuResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("AI応答の解析に失敗しました: " + e.getMessage(), e);
        }
    }
}
