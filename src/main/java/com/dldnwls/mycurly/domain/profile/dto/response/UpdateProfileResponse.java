package com.dldnwls.mycurly.domain.profile.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileResponse {

    private String accountId;

    private String password;

    private String nickName;

}
