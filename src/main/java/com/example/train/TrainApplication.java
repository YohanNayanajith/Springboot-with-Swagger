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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class TrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/login").allowedOrigins("http://localhost:8080");
                registry.addMapping("/api/user/**").allowedOrigins("http://localhost:8080");
                registry.addMapping("/api/swagger-ui/**").allowedOrigins("http://localhost:8080");
                registry.addMapping("/swagger-ui/**").allowedOrigins("http://localhost:8080");
                registry.addMapping("/v2/api-docs").allowedOrigins("http://localhost:8080");
            }
        };
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
