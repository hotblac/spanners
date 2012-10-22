package org.dontpanic.spanners.security;

import org.dontpanic.spanners.dao.Spanner;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Override sstandard Spring ACL based security for one with an awareness of the subtleties of spanners.
 * User: Stevie
 * Date: 11/08/12
 */
public class SpannerPermissionEvaluator implements PermissionEvaluator{

    public static final String OWNER = "owner";

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        boolean hasPermission = false;
        if (targetDomainObject instanceof Spanner && permission.toString().equals(OWNER)) {
            Spanner spanner = (Spanner)targetDomainObject;
            hasPermission = authentication.getName().equals(spanner.getOwner());
        }
        return hasPermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;  // Unsupported for now
    }
}
