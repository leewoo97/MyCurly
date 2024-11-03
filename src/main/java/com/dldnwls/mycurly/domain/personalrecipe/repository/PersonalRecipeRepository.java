package com.dldnwls.mycurly.domain.personalrecipe.repository;

import com.dldnwls.mycurly.domain.personalrecipe.entity.PersonalRecipe;
import com.dldnwls.mycurly.domain.personalrecipe.repository.custom.PersonalRecipeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRecipeRepository extends JpaRepository<PersonalRecipe, Long>, PersonalRecipeRepositoryCustom {
}
