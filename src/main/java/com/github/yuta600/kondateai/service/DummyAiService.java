package com.github.yuta600.kondateai.service;

import com.github.yuta600.kondateai.dto.request.MenuRequest;
import com.github.yuta600.kondateai.dto.response.MenuResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DummyAiService implements AiService {
    @Override
    public MenuResponse getMenuFromAi(MenuRequest Request) {
        return createDummyResponse();
    }

    private MenuResponse createDummyResponse() {
        MenuResponse menuResponse = new MenuResponse();
        List<MenuResponse.DailyMenu> menus = new ArrayList<>();

        MenuResponse.DailyMenu monday = new MenuResponse.DailyMenu();
        monday.setDay("月曜日");
        monday.setTitle("カレーライス");
        monday.setIngredients(createIngredients("豚肉","300g","カレールー","1箱"));
        monday.setCost(1000);
        menus.add(monday);

        MenuResponse.DailyMenu tuesday = new MenuResponse.DailyMenu();
        tuesday.setDay("火曜日");
        tuesday.setTitle("鶏ハム");
        tuesday.setIngredients(createIngredients("鶏肉","150g","塩","ひとつまみ"));
        tuesday.setCost(500);
        menus.add(tuesday);

        MenuResponse.DailyMenu wednesday = new MenuResponse.DailyMenu();
        wednesday.setDay("水曜日");
        wednesday.setTitle("鍋");
        wednesday.setIngredients(createIngredients("牛肉","500g","鍋の素","1袋"));
        wednesday.setCost(1000);
        menus.add(wednesday);

        List<String> shoppingList = new ArrayList<>();
        shoppingList.add("豚肉 300g");
        shoppingList.add("カレールー 1箱");
        shoppingList.add("鶏肉 150g");
        shoppingList.add("塩 ひとつまみ");
        shoppingList.add("牛肉 500g");
        shoppingList.add("鍋の素 1袋");


        menuResponse.setMenus(menus);
        menuResponse.setShoppingList(shoppingList);

        int totalCost = monday.getCost() + tuesday.getCost() + wednesday.getCost();
        menuResponse.setTotalCost(totalCost);

        return menuResponse;
    }

    private List<MenuResponse.Ingredient> createIngredients(String... items) {
        List<MenuResponse.Ingredient> ingredients = new ArrayList<>();
        // 材料名と分量のペアで処理（2つずつ）
        for (int i = 0; i < items.length; i += 2){
            MenuResponse.Ingredient ing = new MenuResponse.Ingredient();
            ing.setName(items[i]);
            ing.setQuantity(items[i + 1]);
            ingredients.add(ing);
        }
        return ingredients;
    }
}
