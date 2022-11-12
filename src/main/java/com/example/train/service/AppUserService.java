package com.example.train.service;

import com.example.train.model.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser saveAppUser(AppUser appUser);
    List<AppUser> getAppUsers();
    AppUser getAppUser(Long id);
    void deleteAppUser(Long id);
    AppUser updateAppUser(Long id,AppUser appUser);

    AppUser getAppUserByUsername(String name);
}
