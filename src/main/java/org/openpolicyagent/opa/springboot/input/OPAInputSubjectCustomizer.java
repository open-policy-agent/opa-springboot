package org.openpolicyagent.opa.springboot.input;

import java.util.Map;

import org.openpolicyagent.opa.springboot.autoconfigure.OPAProperties;
import org.openpolicyagent.opa.springboot.input.InputConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

/**
 * By defining a bean which implements this interface, clients could customize
 * OPA {@code input.subject}.
 */
@FunctionalInterface
public interface OPAInputSubjectCustomizer {

    /**
     * Customizes {@code subject} {@link Map}.
     * 
     * @param subject contains:
     *                <ul>
     *                <li>{@value InputConstants#SUBJECT_TYPE}:
     *                {@value OPAProperties.Request.Subject#DEFAULT_TYPE}
     *                (configurable via {@code opa.request.subject.type}
     *                property)</li>
     *                <li>{@value InputConstants#SUBJECT_ID}:
     *                {@link Authentication#getPrincipal()}</li>
     *                <li>{@value InputConstants#SUBJECT_DETAILS}:
     *                {@link Authentication#getDetails()}</li>
     *                <li>{@value InputConstants#SUBJECT_AUTHORITIES}:
     *                {@link Authentication#getAuthorities()}</li>
     *                </ul>
     * @return should at least contains these keys:
     *         <ul>
     *         <li>{@value InputConstants#SUBJECT_TYPE}</li>
     *         <li>{@value InputConstants#SUBJECT_ID}</li>
     *         </ul>
     */
    Map<String, Object> customize(Authentication authentication,
            RequestAuthorizationContext requestAuthorizationContext, Map<String, Object> subject);
}
