package com.dldnwls.mycurly.domain.personalrecipe.service.impl;

import ch.qos.logback.core.model.processor.ProcessorException;
import com.dldnwls.mycurly.domain.ingredient.entity.Ingredient;
import com.dldnwls.mycurly.domain.ingredient.repository.IngredientRepository;
import com.dldnwls.mycurly.domain.personalrecipe.dto.request.CreatePersonalRecipeRequest;
import com.dldnwls.mycurly.domain.personalrecipe.dto.request.UpdatePersonalRecipeRequest;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.CreatePersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.DeletePersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.GetPersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.UpdatePersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.entity.PersonalRecipe;
import com.dldnwls.mycurly.domain.personalrecipe.repository.PersonalRecipeRepository;
import com.dldnwls.mycurly.domain.personalrecipe.service.PersonalRecipeService;
import com.dldnwls.mycurly.domain.profile.entity.Profile;
import com.dldnwls.mycurly.domain.profile.repository.ProfileRepository;
import com.dldnwls.mycurly.domain.recipeingredient.entity.RecipeIngredient;
import com.dldnwls.mycurly.domain.recipeingredient.repository.RecipeIngredientRepository;
import com.dldnwls.mycurly.global.exception.PersonalRecipeNotFoundException;
import com.dldnwls.mycurly.global.exception.ProfileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class PersonalRecipeServiceImpl implements PersonalRecipeService {

    private final ProfileRepository profileRepository;
    private final PersonalRecipeRepository personalRecipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public CreatePersonalRecipeResponse createPersonalRecipe(Long profileId, CreatePersonalRecipeRequest request) {

        // 1. 프로필 조회
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID: " + profileId));

        // 2. PersonalRecipe 생성 및 저장
        PersonalRecipe personalRecipe = PersonalRecipe.builder()
                .name(request.getName())
                .profile(profile)
                .build();

        // 2-1. 재료 리스트 생성 또는 조회
        Set<Ingredient> ingredientSet = request.getIngredientNameList().stream()
                .map(this::findOrCreateIngredient)
                .collect(Collectors.toSet());

        personalRecipeRepository.save(personalRecipe); // 영속화
        ingredientRepository.saveAll(ingredientSet); //영속화

        // 3. RecipeIngredient Set 생성 및 저장
        Set<RecipeIngredient> recipeIngredientSet = createRecipeIngredientSet(personalRecipe, ingredientSet);

        // 4. PersonalRecipe에 RecipeIngredient 설정 (양방향 관계 설정)
        recipeIngredientSet.stream()
                        .map(recipeIngredient -> personalRecipe.getRecipeIngredientSet().add(recipeIngredient));


        // 5. Ingredient에 RecipeIngredient 설정 (양방향 관계 설정)
        for(Ingredient ingredient : ingredientSet){
            for(RecipeIngredient recipeIngredient : recipeIngredientSet){
                ingredient.getRecipeIngredientSet().add(recipeIngredient);
            }
        }


        // 5. 응답 객체 생성 및 반환
        return CreatePersonalRecipeResponse.builder()
                .name(personalRecipe.getName())
                .nickName(personalRecipe.getProfile().getNickName())
                .ingredientList(recipeIngredientSet.stream()
                        .map(recipeIngredient -> recipeIngredient.getIngredient().getName())
                        .toList())
                .build();
    }

    @Override
    public GetPersonalRecipeResponse getPersonalRecipe(Long profileId, Long personalRecipeId) {

        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ProfileNotFoundException(profileId+""));

        PersonalRecipe personalRecipe = personalRecipeRepository.findById(personalRecipeId).orElseThrow(() -> new PersonalRecipeNotFoundException());

        return GetPersonalRecipeResponse.builder()
                .name(personalRecipe.getName())
                .recipeIngredientNameSet(createRecipeIngredientNameSet(personalRecipe.getRecipeIngredientSet()))
                .build();
    }

    @Override
    public UpdatePersonalRecipeResponse updatePersonalRecipe(Long profileId, Long personalRecipeId, UpdatePersonalRecipeRequest updatePersonalRecipeRequest) {

        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ProfileNotFoundException(profileId+""));

        PersonalRecipe personalRecipe = personalRecipeRepository.findById(personalRecipeId).orElseThrow(PersonalRecipeNotFoundException::new);

        Set<Ingredient> ingredientSet = updatePersonalRecipeRequest.getUpdateIngredientSet().stream()
                .map(this::findOrCreateIngredient)
                .collect(Collectors.toSet());

        personalRecipe.updateName(updatePersonalRecipeRequest.getName())
                .updateRecipeIngredientSet(createRecipeIngredientSet(personalRecipe, ingredientSet));

        return UpdatePersonalRecipeResponse.builder()
                .accountId(profile.getAccountId())
                .nickName(profile.getNickName())
                .name(personalRecipe.getName())
                .ingredientSet(ingredientSet.stream()
                        .map(ingredient -> ingredient.getName())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public void deletePersonalRecipe(Long profileId, Long personalRecipeId) {

        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ProfileNotFoundException(profileId+""));

        // 개인 레시피 존재 여부 확인 및 삭제
        PersonalRecipe recipe = personalRecipeRepository.findById(personalRecipeId)
                .orElseThrow(() -> new PersonalRecipeNotFoundException());

        personalRecipeRepository.delete(recipe);
    }

    /**
     * RecipeIngredient Set 생성
     */
    private Set<RecipeIngredient> createRecipeIngredientSet(PersonalRecipe personalRecipe, Set<Ingredient> ingredientSet) {

        // 1. RecipeIngredient 리스트 생성
        return ingredientSet.stream()
                .map(ingredient -> RecipeIngredient.builder()
                        .personalRecipe(personalRecipe)
                        .ingredient(ingredient)
                        .build())
                .collect(Collectors.toSet());
    }

    /**
     * RecipeIngredientName Set 생성
     */
    private Set<String> createRecipeIngredientNameSet(Set<RecipeIngredient> recipeIngredientSet){

        return recipeIngredientSet.stream()
                .map(recipeIngredient -> recipeIngredient.getIngredient().getName())
                .collect(Collectors.toSet());
    }

    /**
     * 재료 조회 또는 새로 생성
     */
    private Ingredient findOrCreateIngredient(String name) {
        return ingredientRepository.findByName(name)
                .orElseGet(() -> ingredientRepository.save(Ingredient.builder()
                        .name(name)
                        .build()));
    }
}

