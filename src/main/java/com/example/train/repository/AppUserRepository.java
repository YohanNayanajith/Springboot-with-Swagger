package com.example.train.repository;

import com.example.train.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppUserRepository extends JpaRepository<AppUser,Long>, JpaSpecificationExecutor<AppUser> {
    AppUser findAppUserByUserName(String name);
}
