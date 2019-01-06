package com.masmovil.iam.api;

import com.masmovil.iam.config.UsersConfig;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

@Filter("/masmovil/iam/v1/users/**")
@Slf4j
public class UsersFilter implements HttpClientFilter {

    private UsersConfig usersConfig;

    public UsersFilter(UsersConfig usersConfig) {
        this.usersConfig = usersConfig;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        log.info("en el filtro para la autenticacion {}",usersConfig.getBearerToken());
        return chain.proceed(request.bearerAuth(usersConfig.getBearerToken()));
    }
}
