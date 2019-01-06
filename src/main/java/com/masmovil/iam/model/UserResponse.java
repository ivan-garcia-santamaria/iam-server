package com.masmovil.iam.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UserResponse extends User {
    private String sfid;
    private String email;
    private String username;
    private String password;

    @Builder
    public UserResponse(UserRequest user) {
        super(user.getUserId(), user.getGivenName(),
                user.getFamilyName(),
                user.getSencondFamilyName());

    }
}
