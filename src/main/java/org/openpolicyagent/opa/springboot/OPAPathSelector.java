package org.openpolicyagent.opa.springboot;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

/**
 * Selects target OPA path based on {@link Authentication},
 * {@link RequestAuthorizationContext}, and input {@link Map}.
 */
@FunctionalInterface
public interface OPAPathSelector {
    String selectPath(Authentication authentication, RequestAuthorizationContext requestAuthorizationContext,
            Map<String, Object> opaInput);
}
