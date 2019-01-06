package com.masmovil.iam.api;

import com.masmovil.iam.config.UsersConfig;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Maybe;

import java.util.List;

@Client(UsersConfig.API_URL)
public interface UsersClient {

    @Get("/masmovil/iam/v1/users")
    Maybe<List<ApiUser>> fetchUsers();
}
