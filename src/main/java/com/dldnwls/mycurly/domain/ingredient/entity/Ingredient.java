package com.dldnwls.mycurly.domain.ingredient.entity;

import com.dldnwls.mycurly.domain.personalrecipe.entity.PersonalRecipe;
import com.dldnwls.mycurly.domain.recipeingredient.entity.RecipeIngredient;
import com.dldnwls.mycurly.domain.standardrecipe.entity.StandardRecipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredient { //재료

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; //재료 명

    @Builder.Default
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.PERSIST)
    private Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();
}
