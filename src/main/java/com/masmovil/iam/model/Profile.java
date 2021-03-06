package com.masmovil.iam.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Profile {
    private String id;
    private String name;
    private String description;
}
