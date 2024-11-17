package com.dldnwls.mycurly.domain.personalrecipe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UpdatePersonalRecipeRequest {

    private String name; //레시피 명

    private Set<String> updateIngredientSet; //변경할 재료 목록
}
