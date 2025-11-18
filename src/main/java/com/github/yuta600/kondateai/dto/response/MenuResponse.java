package com.github.yuta600.kondateai.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MenuResponse {
    private List<DailyMenu> menus;
    private List<String> shoppingList;
    private int totalCost;

    @Data
    public static class DailyMenu {
        private String day;
        private String title;
        private List<Ingredient> ingredients;
        private int cost;

        // MVP完成後に追加予定:レシピURL
        // private String recipeUrl;
    }

    @Data
    public static class Ingredient {
        private String name;
        private String quantity;
    }

}
