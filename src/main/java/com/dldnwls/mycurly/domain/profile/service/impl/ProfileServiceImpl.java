package com.dldnwls.mycurly.domain.profile.service.impl;

import com.dldnwls.mycurly.domain.profile.dto.request.CreateProfileRequest;
import com.dldnwls.mycurly.domain.profile.dto.request.UpdateProfileRequest;
import com.dldnwls.mycurly.domain.profile.dto.response.CreateProfileResponse;
import com.dldnwls.mycurly.domain.profile.dto.response.GetProfileResponse;
import com.dldnwls.mycurly.domain.profile.dto.response.UpdateProfileResponse;
import com.dldnwls.mycurly.domain.profile.entity.Profile;
import com.dldnwls.mycurly.domain.profile.repository.ProfileRepository;
import com.dldnwls.mycurly.domain.profile.service.ProfileService;
import com.dldnwls.mycurly.global.exception.ProfileNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    @Override
    public CreateProfileResponse createProfile(CreateProfileRequest createProfileRequest) {

        Profile profile = Profile.builder()
                .accountId(createProfileRequest.getAccountId())
                .password(createProfileRequest.getPassword())
                .nickName(createProfileRequest.getNickName())
                .build();

        profileRepository.save(profile);

        return modelMapper.map(profile,CreateProfileResponse.class);
    }

    @Override
    public GetProfileResponse getProfile(Long profileId) {

        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ProfileNotFoundException("profile not found with id : " + profileId));

        return modelMapper.map(profile, GetProfileResponse.class);
    }

    @Override
    public UpdateProfileResponse updateProfile(Long profileId, UpdateProfileRequest updateProfileRequest) {

        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new ProfileNotFoundException("profile not found with id : " + profileId));

        profile.updateAccountId(updateProfileRequest.getAccountId())
                .updatePassword(updateProfileRequest.getPassword())
                .updateNickName(updateProfileRequest.getNickName());

        return modelMapper.map(profile, UpdateProfileResponse.class);
    }

    @Override
    public boolean deleteProfile(Long profileId) {

        if(profileRepository.existsById(profileId)){
            profileRepository.deleteById(profileId);
            return true;
        }else{
            return false;
        }
    }

}
