package io.github.open_policy_agent.opa.springboot.input;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import jakarta.servlet.http.HttpServletRequest;

/**
 * By defining a bean which implements this interface, clients could customize
 * OPA {@code input.action}.
 */
@FunctionalInterface
public interface OPAInputActionCustomizer {

    /**
     * Customizes {@code action} {@link Map}.
     * 
     * @param action contains:
     *               <ul>
     *               <li>{@value InputConstants#ACTION_NAME}:
     *               {@link HttpServletRequest#getMethod()}</li>
     *               <li>{@value InputConstants#ACTION_PROTOCOL}:
     *               {@link HttpServletRequest#getProtocol()}</li>
     *               <li>{@value InputConstants#ACTION_HEADERS}:
     *               {@link HttpServletRequest} headers</li>
     *               </ul>
     * @return should at least contains this key:
     *         <ul>
     *         <li>{@value InputConstants#ACTION_NAME}</li>
     *         </ul>
     */
    Map<String, Object> customize(Authentication authentication,
            RequestAuthorizationContext requestAuthorizationContext, Map<String, Object> action);
}
