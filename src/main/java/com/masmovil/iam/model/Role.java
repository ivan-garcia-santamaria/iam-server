package com.masmovil.iam.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private String id;
    private String idPermission;
    private String idProfile;
    private String description;
}
