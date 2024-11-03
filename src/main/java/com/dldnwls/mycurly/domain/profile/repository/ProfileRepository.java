package com.dldnwls.mycurly.domain.profile.repository;

import com.dldnwls.mycurly.domain.profile.entity.Profile;
import com.dldnwls.mycurly.domain.profile.repository.custom.ProfileRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom {
}
