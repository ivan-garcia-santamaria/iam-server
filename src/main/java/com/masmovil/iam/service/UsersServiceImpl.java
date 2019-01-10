package com.masmovil.iam.service;

import com.masmovil.iam.api.ApiUser;
import com.masmovil.iam.api.UsersLowLevelClient;
import com.masmovil.iam.config.UsersConfig;
import com.masmovil.iam.model.UserRequest;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.List;
import java.util.UUID;

@Slf4j
@Singleton
public class UsersServiceImpl implements UsersService {

    private final UsersConfig configuration;
    private final UsersLowLevelClient usersLowLevelClient;

    public UsersServiceImpl(UsersConfig configuration,
                            UsersLowLevelClient usersLowLevelClient) {
        this.configuration = configuration;
        this.usersLowLevelClient=usersLowLevelClient;
    }

    @Override
    public String getUsername(UserRequest user) {

        /**
         * iteramos hasta que encontremos un unico
         * cn a traves de los apellidos, partimos de 5 caracteres
         */
        String apellido2=user.getSecondFamilyName().toLowerCase().replace(" ", "");


        /**
         * iteramos hasta que encontremos un unico
         * cn a traves de los apellidos, partimos de 5 caracteres
         */
        StringBuffer nick=new StringBuffer();
        //nick.append(letraNombre);
        nick.append(user.getGivenName().replace(" ", "").toLowerCase())
                .append(".")
                .append(user.getFamilyName().replace(" ", "").toLowerCase());

        boolean existe = true;

        int posicionAp2 = 0;
        do {
            log.info("comprobando nick: " + nick);
            List<ApiUser> apiUsers=usersLowLevelClient.existsUserByNickname(nick.toString()).blockingGet();
            log.info("apiUsers {}",apiUsers);
            existe=!apiUsers.isEmpty();
            log.info("usuario existe -> {}",existe);

            if (existe) {
                /**
                 * añadimos la siguiente letra del segundo apellido
                 * o bien x
                 */
                if (!apellido2.isEmpty()
                        && posicionAp2<apellido2.length()) {
                    String parteAp2 = apellido2.substring(posicionAp2, posicionAp2 + 1);
                    nick.append(parteAp2);
                    posicionAp2++;
                }else{
                    nick.append("x");
                }
            }

        }while (existe);

        log.info("nick: " + nick);

        return nick.toString();
    }

    @Override
    public String getPassword() {
        return this.configuration.getDefaultPassword();
    }

    @Override
    public String getSfid(UserRequest user, String groupOfSfid) {
        if (user.getSfid().isEmpty()) {
            return UUID.randomUUID().toString();
        }else{
            return user.getSfid();
        }
    }

    @Override
    public String getEmail(String username) {
        return username + this.configuration.getEmailDomain();
    }


}
