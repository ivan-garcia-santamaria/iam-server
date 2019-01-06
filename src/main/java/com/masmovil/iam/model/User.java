package com.masmovil.iam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String userId;
    private String givenName;
    private String familyName;
    private String sencondFamilyName;

}
