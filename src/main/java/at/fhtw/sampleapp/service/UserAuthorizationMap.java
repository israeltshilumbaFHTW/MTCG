package at.fhtw.sampleapp.service;

import java.util.HashMap;
import java.util.Map;

public class UserAuthorizationMap {
    //hard Coded
    private static Map<String, Integer> authorization = null;

    private UserAuthorizationMap() {
        authorization = new HashMap<String, Integer>();
        authorization.put("Basic kienboec-mtcgToken", 1);
        authorization.put("Basic altenhof-mtcgToken", 2);
        authorization.put("Basic admin-mtcgToken", 3);
    }

    public static Map<String, Integer> getAuthorization() {
        if (authorization == null) {
           new UserAuthorizationMap();
        }
        return authorization;
    }
}
