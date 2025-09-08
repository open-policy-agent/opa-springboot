package org.openpolicyagent.opa.springboot.input;

import java.util.Map;

import org.openpolicyagent.opa.springboot.autoconfigure.OPAProperties;
import org.openpolicyagent.opa.springboot.input.InputConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import jakarta.servlet.http.HttpServletRequest;

/**
 * By defining a bean which implements this interface, clients could customize
 * OPA {@code input.resource}.
 */
@FunctionalInterface
public interface OPAInputResourceCustomizer {

    /**
     * Customizes {@code resource} {@link Map}.
     * 
     * @param resource contains:
     *                 <ul>
     *                 <li>{@value InputConstants#RESOURCE_TYPE}:
     *                 {@value OPAProperties.Request.Resource#DEFAULT_TYPE}
     *                 (configurable via {@code opa.request.resource.type}
     *                 property)</li>
     *                 <li>{@value InputConstants#RESOURCE_ID}:
     *                 {@link HttpServletRequest#getServletPath()}</li>
     *                 </ul>
     * @return should at least contains these keys:
     *         <ul>
     *         <li>{@value InputConstants#RESOURCE_TYPE}</li>
     *         <li>{@value InputConstants#RESOURCE_ID}</li>
     *         </ul>
     */
    Map<String, Object> customize(Authentication authentication,
            RequestAuthorizationContext requestAuthorizationContext,
            Map<String, Object> resource);
}
