package com.dldnwls.mycurly.domain.profile.service;

import com.dldnwls.mycurly.domain.profile.dto.request.CreateProfileRequest;
import com.dldnwls.mycurly.domain.profile.dto.request.UpdateProfileRequest;
import com.dldnwls.mycurly.domain.profile.dto.response.CreateProfileResponse;
import com.dldnwls.mycurly.domain.profile.dto.response.GetProfileResponse;
import com.dldnwls.mycurly.domain.profile.dto.response.UpdateProfileResponse;

public interface ProfileService {

    public CreateProfileResponse createProfile(CreateProfileRequest createProfileRequest);

    public GetProfileResponse getProfile(Long profileId);

    public UpdateProfileResponse updateProfile(Long profileId, UpdateProfileRequest updateProfileRequest);

    public boolean deleteProfile(Long profileId);

}
