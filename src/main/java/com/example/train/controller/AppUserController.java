package com.example.train.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.train.model.AppUser;
import com.example.train.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Api(tags = "User")
@RestController
@RequestMapping("/api/user")
@CrossOrigin
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @ApiOperation(value = "all users", notes = "Get all users")
    @GetMapping("/")
    public ResponseEntity<List<AppUser>> getAppUsers(){
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getAppUser(@PathVariable Long id){
        return new ResponseEntity<AppUser>(appUserService.getAppUser(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<AppUser> saveAppUser(@RequestBody AppUser appUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveAppUser(appUser));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AppUser> updateAppUser(@RequestBody AppUser appUser, @PathVariable Long id){
        try{
//            Student existingStudent = studentService.StudentGet(id);
            AppUser appUserNew = appUserService.updateAppUser(id,appUser);
//            return new ResponseEntity<>(HttpStatus.OK);
            return ResponseEntity.ok().body(appUserNew);
        }catch (NoSuchElementException e){
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAppUser(@PathVariable Long id){
        appUserService.deleteAppUser(id);
        return "Deleted user with id "+id;
    }

//    @GetMapping("/login")
//    public ResponseEntity<List<String>> updateAppUser(@RequestBody String username, @RequestBody String password){
//        try{
////            Student existingStudent = studentService.StudentGet(id);
//            AppUser appUserNew = appUserService.updateAppUser(id,appUser);
////            return new ResponseEntity<>(HttpStatus.OK);
//            return ResponseEntity.ok().body(appUserNew);
//        }catch (NoSuchElementException e){
//            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
//        }
//    }
//    public void refreshToken (HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//        String access_token = JWT.create()
//                .withSubject(request.)
//                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*1000))
//                .withIssuer(request.getRequestURL().toString())
//                .sign(algorithm);
//    }
}
