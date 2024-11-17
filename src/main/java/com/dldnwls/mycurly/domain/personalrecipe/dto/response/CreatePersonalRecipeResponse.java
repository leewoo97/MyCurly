package com.dldnwls.mycurly.domain.personalrecipe.dto.response;

import com.dldnwls.mycurly.domain.profile.dto.response.ProfileResponse;
import com.dldnwls.mycurly.domain.profile.entity.Profile;
import com.dldnwls.mycurly.domain.recipeingredient.dto.response.RecipeIngredientResponse;
import com.dldnwls.mycurly.domain.recipeingredient.entity.RecipeIngredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CreatePersonalRecipeResponse {

    private String name; //레시피 명

    private String accountId; //회원 아이디

    private String nickName; //회원 닉네임

    private List<String> ingredientList; //레시피 재료 목록
}
