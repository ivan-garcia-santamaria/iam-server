package com.masmovil.iam.api;

import lombok.Data;

@Data
public class ApiUser {
    private String email;
    private String given_name;
    private String family_name;
    private String name;
    private String username;
    private String nickname;
    private String user_id;
    private boolean blocked;
}
