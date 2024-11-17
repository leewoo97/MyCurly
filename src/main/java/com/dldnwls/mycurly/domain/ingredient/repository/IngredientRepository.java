package com.dldnwls.mycurly.domain.ingredient.repository;

import com.dldnwls.mycurly.domain.ingredient.entity.Ingredient;
import com.dldnwls.mycurly.domain.ingredient.repository.custom.IngredientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>, IngredientRepositoryCustom {

    boolean existsByName(String name);

    Optional<Ingredient> findByName(String name);
}
