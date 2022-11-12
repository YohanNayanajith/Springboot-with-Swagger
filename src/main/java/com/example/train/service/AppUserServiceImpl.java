package com.example.train.service;

import com.example.train.model.AppUser;
import com.example.train.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUser saveAppUser(AppUser appUser){
        log.info("Saving user {} to the database",appUser.getUserName());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }
    public List<AppUser> getAppUsers(){
        log.info("Get all users from database");
        return appUserRepository.findAll();
    }
    public AppUser getAppUser(Long id){
        log.info("Get {} user from database",id);
        return appUserRepository.findById(id).get();
    }
    public void deleteAppUser(Long id){
        log.info("Delete user {} from database",id);
        appUserRepository.deleteById(id);
    }
    public AppUser updateAppUser(Long id,AppUser appUser){
        AppUser existingAppUser = appUserRepository.findById(id).get();

        if(Objects.nonNull(appUser.getUserName()) && !"".equalsIgnoreCase(appUser.getUserName())){
            existingAppUser.setUserName(appUser.getUserName());
        }
        return appUserRepository.save(existingAppUser);
    }
    public AppUser getAppUserByUsername(String name){
        AppUser appUser = appUserRepository.findAppUserByUserName(name);
        if(appUser == null){
            log.error("User {} cannot find in the database",name);
        }else {
            log.info("User {} found in the database",name);
        }
        return appUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByUserName(username);
        if(appUser == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User found in the database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        appUser.getUserName().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getName())));
        return new org.springframework.security.core.userdetails.User(appUser.getUserName(), appUser.getPassword(), authorities);
    }
}
