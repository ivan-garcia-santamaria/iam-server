package com.masmovil.iam.api;

import com.masmovil.iam.config.UsersConfig;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
@Slf4j
public class UsersLowLevelClient {

    private final RxHttpClient httpClient;

    public UsersLowLevelClient(@Client(UsersConfig.API_URL) RxHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Maybe<List<ApiUser>> fetchUsers() {
        final String uri = "/masmovil/iam/v1/users";
        HttpRequest<?> req = HttpRequest.GET(uri);
        Flowable flowable = httpClient.retrieve(req, Argument.of(List.class, ApiUser.class));
        return (Maybe<List<ApiUser>>) flowable.firstElement();
    }

    public Maybe<List<ApiUser>> existsUserByNickname(String nickname) {
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", nickname);

        String path = "/masmovil/iam/v1/users?q=nickname:{nickname}&search_engine=v3";
        final String uri = UriTemplate.of(path).expand(map);

        log.info("uri a atacar: {}",uri);

        HttpRequest<?> req = HttpRequest.GET(uri);
        Flowable flowable = httpClient.retrieve(req, Argument.of(List.class, ApiUser.class));
        return (Maybe<List<ApiUser>>) flowable.firstElement();
    }
}
