package com.dldnwls.mycurly.domain.ingredient.repository;

import com.dldnwls.mycurly.domain.ingredient.entity.Ingredient;
import com.dldnwls.mycurly.domain.ingredient.repository.custom.IngredientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>, IngredientRepositoryCustom {
}
