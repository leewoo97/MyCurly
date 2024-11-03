package com.dldnwls.mycurly.domain.profile.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {

    private String accountId;

    private String password;

    private String nickName;

}
