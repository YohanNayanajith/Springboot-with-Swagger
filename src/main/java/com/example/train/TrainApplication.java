package com.example.train;

import com.example.train.model.AppUser;
import com.example.train.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class TrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Initially,added data to the database using command line runner
//    @Bean
//    CommandLineRunner run(AppUserService appUserService){
//        return args -> {
//            appUserService.saveAppUser(new AppUser(null,"Kaveesha","123","kaveesha@gmail.com","0758657450",true));
//        };
//    }

}
