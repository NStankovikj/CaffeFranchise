package com.asseco.login;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class RollerRolesAuthorizationFilter
        extends RolesAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(
            ServletRequest request,
            ServletResponse response,
            Object mappedValue) throws IOException {

        final Subject subject = getSubject(request, response);
        final String[] roles = (String[]) mappedValue;

        if (roles == null || roles.length == 0) {
            return true;
        }

        // user is authorized if they have ANY of the roles
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
        return false;
    }
}