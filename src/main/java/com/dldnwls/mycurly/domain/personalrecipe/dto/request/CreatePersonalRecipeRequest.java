package com.dldnwls.mycurly.domain.personalrecipe.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreatePersonalRecipeRequest {

    private String name; //레시피 명

    private List<String> ingredientNameList = new ArrayList<>(); //들어가는 재료 이름 리스트
}
