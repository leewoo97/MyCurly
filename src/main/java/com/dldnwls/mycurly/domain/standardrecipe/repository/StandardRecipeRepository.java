package com.dldnwls.mycurly.domain.standardrecipe.repository;

import com.dldnwls.mycurly.domain.standardrecipe.entity.StandardRecipe;
import com.dldnwls.mycurly.domain.standardrecipe.repository.custom.StandardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardRecipeRepository extends JpaRepository<StandardRecipe, Long>, StandardRepositoryCustom {
}
