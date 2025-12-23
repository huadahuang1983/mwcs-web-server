package com.reebake.mwcs.core;

import com.reebake.mwcs.security.model.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContextHolder {

    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null
                || authentication.getPrincipal() == null
                || !(authentication.getPrincipal() instanceof AuthUser)) {
            return null;
        }
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return authUser.getUserId();
    }

}
