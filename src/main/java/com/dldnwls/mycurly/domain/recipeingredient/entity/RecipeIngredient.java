package com.dldnwls.mycurly.domain.recipeingredient.entity;

import com.dldnwls.mycurly.domain.ingredient.entity.Ingredient;
import com.dldnwls.mycurly.domain.personalrecipe.entity.PersonalRecipe;
import com.dldnwls.mycurly.domain.standardrecipe.entity.StandardRecipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecipeIngredient { //개인레시피, 정규레시피, 재료 테이블을 잇는 중간테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonalRecipe personalRecipe;

    @ManyToOne(fetch = FetchType.LAZY)
    private StandardRecipe standardRecipe;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

}
