package com.shiro.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm implements Realm {
    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!"zhang".equals((String) authenticationToken.getPrincipal().toString())) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(new String((char[])authenticationToken.getCredentials()))) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo((String )authenticationToken.getPrincipal().toString(),(String )authenticationToken.getCredentials().toString(),getName());
    }
}
