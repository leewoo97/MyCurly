package com.dldnwls.mycurly.domain.ingredient.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class IngredientResponse {

    private String name; //재료 명

}
