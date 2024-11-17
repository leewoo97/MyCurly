package com.dldnwls.mycurly.domain.recipeingredient.dto.response;

import com.dldnwls.mycurly.domain.ingredient.dto.response.IngredientResponse;
import com.dldnwls.mycurly.domain.ingredient.entity.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class RecipeIngredientResponse {

    private IngredientResponse ingredientResponse;

}
