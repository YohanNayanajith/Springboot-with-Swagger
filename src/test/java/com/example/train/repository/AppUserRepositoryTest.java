package com.example.train.repository;

import com.example.train.model.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;

    @Test
    void testFindAppUserByUserName() {
        //given
        Long id = 1L;
        String name = "Kaveesha";
        String password = "123";
        String email = "kaveesha@gmail.com";
        String contactNumber = "0986867676";
        boolean isActivate = true;

        AppUser appUser = new AppUser(id,name,password,email,contactNumber,isActivate);
        underTest.save(appUser);

        //when
        AppUser expected = underTest.findAppUserByUserName(name);

        //then
        assertThat(expected).isNotNull();
    }
}