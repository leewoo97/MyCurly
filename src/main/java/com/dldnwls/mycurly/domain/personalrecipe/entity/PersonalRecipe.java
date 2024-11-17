package com.dldnwls.mycurly.domain.personalrecipe.entity;

import com.dldnwls.mycurly.domain.ingredient.entity.Ingredient;
import com.dldnwls.mycurly.domain.profile.entity.Profile;
import com.dldnwls.mycurly.domain.recipeingredient.entity.RecipeIngredient;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonalRecipe { //개인 레시피

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; //레시피 명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "personalRecipe", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<RecipeIngredient> recipeIngredientSet = new HashSet<>();

    public PersonalRecipe updateName(String name) {
        this.name = name;
        return this;
    }

    public PersonalRecipe updateRecipeIngredientSet(Set<RecipeIngredient> recipeIngredientSet) {
        this.recipeIngredientSet = recipeIngredientSet;
        return this;
    }
}
