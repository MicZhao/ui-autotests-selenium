package io.miczhao.userManager;

import com.typesafe.config.Config;
import io.miczhao.commons.BaseConfigProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public interface UserConfigProvider extends BaseConfigProvider {

    Config USERS_CONFIG = BaseConfigProvider.readConfig("/application/app.conf");

    String commonPass = USERS_CONFIG.getString("common-password");

    List<User> users = Arrays
            .stream(((Object[]) USERS_CONFIG.getAnyRef("users")))
            .sequential()
            .map(userAsObject -> {
                HashMap<String, String> userAsHashMap = (HashMap<String, String>) userAsObject;
                return new User(userAsHashMap.get("login"), userAsHashMap.get("password"));
            })
            .collect(Collectors.toList());
}
