package com.masmovil.iam.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Data;

@ConfigurationProperties(UsersConfig.PREFIX)
@Requires(property = UsersConfig.PREFIX)
@Data
public class UsersConfig {
    public static final String PREFIX = "users";
    public static final String API_URL = "https://masmovil-test-dev.apigee.net";

    private String emailDomain;
    private int usernameMinLength;
    private String defaultPassword;

    private String bearerToken;

}
