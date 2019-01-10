package com.masmovil.iam.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String userId;
    private String givenName;
    private String familyName;
    private String secondFamilyName;
    private String sfid;

    private String groupOfSfid;

}
