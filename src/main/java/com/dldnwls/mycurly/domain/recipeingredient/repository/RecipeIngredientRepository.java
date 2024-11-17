package com.dldnwls.mycurly.domain.recipeingredient.repository;

import com.dldnwls.mycurly.domain.recipeingredient.entity.RecipeIngredient;
import com.dldnwls.mycurly.domain.recipeingredient.repository.custom.RecipeIngredientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long>, RecipeIngredientRepositoryCustom {
}
