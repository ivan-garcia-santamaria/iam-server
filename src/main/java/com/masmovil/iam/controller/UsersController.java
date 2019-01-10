package com.masmovil.iam.controller;

import com.masmovil.iam.config.UsersConfig;
import com.masmovil.iam.model.UserRequest;
import com.masmovil.iam.model.UserResponse;
import com.masmovil.iam.service.UsersService;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import lombok.extern.slf4j.Slf4j;

@Controller("/iam/v1/users")
@Slf4j
public class UsersController {

    private UsersService usersService;
    private UsersConfig usersConfig;

    public UsersController(UsersService usersService,
                           UsersConfig usersConfig) {
        this.usersService = usersService;
        this.usersConfig=usersConfig;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Post("/")
    public UserResponse generateCredentials(final HttpHeaders headers, @Body UserRequest userRequest)
    {

        log.info("userRequest {}",userRequest);
        String bearer=headers.getAuthorization().get();
        log.info("{}",bearer);
        usersConfig.setBearerToken(bearer);
        UserResponse userResponse=UserResponse.builder().user(userRequest).build();

        userResponse.setSfid(usersService.getSfid(userRequest,userRequest.getGroupOfSfid()));

        userResponse.setUsername(usersService.getUsername(userRequest));
        userResponse.setEmail(usersService.getEmail(userResponse.getUsername()));
        userResponse.setPassword(usersService.getPassword());

        return userResponse;
    }

}
