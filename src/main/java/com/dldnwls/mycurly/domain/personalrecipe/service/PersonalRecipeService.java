package com.dldnwls.mycurly.domain.personalrecipe.service;

import com.dldnwls.mycurly.domain.personalrecipe.dto.request.CreatePersonalRecipeRequest;
import com.dldnwls.mycurly.domain.personalrecipe.dto.request.UpdatePersonalRecipeRequest;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.CreatePersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.DeletePersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.GetPersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.UpdatePersonalRecipeResponse;
import com.dldnwls.mycurly.domain.profile.dto.request.CreateProfileRequest;
import com.dldnwls.mycurly.domain.profile.dto.response.CreateProfileResponse;

public interface PersonalRecipeService {

    CreatePersonalRecipeResponse createPersonalRecipe(Long profileId, CreatePersonalRecipeRequest createPersonalRecipeRequest);

    GetPersonalRecipeResponse getPersonalRecipe(Long profileId, Long personalRecipeId);

    UpdatePersonalRecipeResponse updatePersonalRecipe(Long profileId, Long personalRecipeId, UpdatePersonalRecipeRequest updatePersonalRecipeRequest);

    void deletePersonalRecipe(Long profileId, Long personalRecipeId);

}