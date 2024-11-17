package com.dldnwls.mycurly.domain.profile.dto.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponse {

    private String accountId;

    private String password;

    private String nickName;

}
