package com.dldnwls.mycurly.domain.personalrecipe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class UpdatePersonalRecipeResponse {

    private String name; //레시피 명

    private String accountId; //회원 아이디

    private String nickName; //회원 닉네임

    private Set<String> ingredientSet; //레시피 재료 목록

}
