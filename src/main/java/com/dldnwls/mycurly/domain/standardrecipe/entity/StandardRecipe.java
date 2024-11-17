package com.dldnwls.mycurly.domain.standardrecipe.entity;

import com.dldnwls.mycurly.domain.ingredient.entity.Ingredient;
import com.dldnwls.mycurly.domain.recipeingredient.entity.RecipeIngredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class StandardRecipe { //정규 레시피

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name; //레시피 명

    @OneToMany(mappedBy = "standardRecipe")
    private List<RecipeIngredient> recipeIngredientList = new ArrayList<>();
}
