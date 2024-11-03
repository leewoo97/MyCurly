package com.dldnwls.mycurly.domain.profile.controller;

import com.dldnwls.mycurly.domain.profile.dto.request.CreateProfileRequest;
import com.dldnwls.mycurly.domain.profile.dto.request.UpdateProfileRequest;
import com.dldnwls.mycurly.domain.profile.dto.response.CreateProfileResponse;
import com.dldnwls.mycurly.domain.profile.dto.response.GetProfileResponse;
import com.dldnwls.mycurly.domain.profile.dto.response.UpdateProfileResponse;
import com.dldnwls.mycurly.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/profile")
@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final ProfileService profileService;

    //프로필생성 API(= 회원가입 API)
    @PostMapping
    ResponseEntity<CreateProfileResponse> createProfile(@RequestBody CreateProfileRequest createProfileRequest){
        CreateProfileResponse createProfileResponse = profileService.createProfile(createProfileRequest);
        return new ResponseEntity<>(createProfileResponse,HttpStatus.OK);
    }

    //프로필 조회 API
    @GetMapping("/{profileId}")
    ResponseEntity<GetProfileResponse> getProfile(@PathVariable Long profileId){
        GetProfileResponse getProfileResponse = profileService.getProfile(profileId);
        return new ResponseEntity<>(getProfileResponse, HttpStatus.OK);
    }

    //프로필 수정 API
    @PutMapping("/{profileId}")
    ResponseEntity<UpdateProfileResponse> updateProfile(@PathVariable Long profileId, @RequestBody UpdateProfileRequest updateProfileRequest){
        UpdateProfileResponse updateProfileResponse = profileService.updateProfile(profileId, updateProfileRequest);
        return new ResponseEntity<>(updateProfileResponse, HttpStatus.OK);
    }

    //프로필 삭제 API
    @DeleteMapping("/{profileId}")
    ResponseEntity<Void> deleteProfile(@PathVariable Long profileId){
        boolean isDeleted = profileService.deleteProfile(profileId);

        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
