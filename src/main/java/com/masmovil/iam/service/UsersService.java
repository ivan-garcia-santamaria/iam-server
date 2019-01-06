package com.masmovil.iam.service;

import com.masmovil.iam.model.UserRequest;

public interface UsersService {
    public String getUsername(UserRequest user);
    public String getPassword();
    public String getSfid(UserRequest user,String groupOfSfid);
    public String getEmail(String username);
}
