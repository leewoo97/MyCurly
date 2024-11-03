package com.dldnwls.mycurly.domain.profile.entity;

import com.dldnwls.mycurly.domain.personalrecipe.entity.PersonalRecipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", unique = true, nullable = false)
    private String accountId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @OneToMany(mappedBy = "profile")
    private List<PersonalRecipe> personalRecipeList = new ArrayList<>();

    public Profile updateAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public Profile updatePassword(String password) {
        this.password = password;
        return this;
    }

    public Profile updateNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

}
