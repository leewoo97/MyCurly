package com.dldnwls.mycurly.domain.personalrecipe.dto.response;

import com.dldnwls.mycurly.domain.recipeingredient.entity.RecipeIngredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class GetPersonalRecipeResponse {

    private String name; //레시피 명

    private Set<String> recipeIngredientNameSet = new HashSet<>(); //레시피에 들어가는 재료 이름 목록

}
