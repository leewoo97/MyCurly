package com.dldnwls.mycurly.domain.personalrecipe.controller;

import com.dldnwls.mycurly.domain.personalrecipe.dto.request.CreatePersonalRecipeRequest;
import com.dldnwls.mycurly.domain.personalrecipe.dto.request.UpdatePersonalRecipeRequest;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.CreatePersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.GetPersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.dto.response.UpdatePersonalRecipeResponse;
import com.dldnwls.mycurly.domain.personalrecipe.service.PersonalRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/personalrecipe")
@RequiredArgsConstructor
@RestController
public class PersonalRecipeController {

    private final PersonalRecipeService personalRecipeService;

    @PostMapping("/{profileId}")
    private ResponseEntity<CreatePersonalRecipeResponse> createPersonalRecipe(@PathVariable Long profileId, @RequestBody CreatePersonalRecipeRequest createPersonalRecipeRequest){

        CreatePersonalRecipeResponse createPersonalRecipeResponse = personalRecipeService.createPersonalRecipe(profileId, createPersonalRecipeRequest);
        return ResponseEntity.ok(createPersonalRecipeResponse);
    }

    @GetMapping("/{profileId}/{personalRecipeId}")
    private ResponseEntity<GetPersonalRecipeResponse> getPersonalRecipe(@PathVariable Long profileId, @PathVariable Long personalRecipeId){
        GetPersonalRecipeResponse getPersonalRecipeResponse = personalRecipeService.getPersonalRecipe(profileId, personalRecipeId);
        return ResponseEntity.ok(getPersonalRecipeResponse);
    }

    @PutMapping("/{profileId}/{personalRecipeId}")
    private ResponseEntity<UpdatePersonalRecipeResponse> updatePersonalRecipe(@PathVariable Long profileId, @PathVariable Long personalRecipeId, @RequestBody UpdatePersonalRecipeRequest updatePersonalRecipeRequest){

        UpdatePersonalRecipeResponse updatePersonalRecipeResponse = personalRecipeService.updatePersonalRecipe(profileId, personalRecipeId, updatePersonalRecipeRequest);

        return ResponseEntity.ok(updatePersonalRecipeResponse);
    }

    @DeleteMapping("/{profileId}/{personalRecipeId}")
    private ResponseEntity<Void> deletePersonalRecipe(@PathVariable Long profileId, @PathVariable Long personalRecipeId){

        personalRecipeService.deletePersonalRecipe(profileId, personalRecipeId);

        return ResponseEntity.noContent().build();
    }

}
