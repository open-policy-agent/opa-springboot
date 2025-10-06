package io.github.open_policy_agent.opa.springboot.authorization;

import org.springframework.security.access.AccessDeniedException;

import io.github.open_policy_agent.opa.springboot.OPAResponse;

import lombok.Getter;

/**
 * Extends {@link AccessDeniedException} which conveys {@link OPAResponse}.
 */
@Getter
public class OPAAccessDeniedException extends AccessDeniedException {

    private OPAResponse opaResponse;

    public OPAAccessDeniedException(String message) {
        super(message);
    }

    public OPAAccessDeniedException(String message, OPAResponse opaResponse) {
        super(message);
        this.opaResponse = opaResponse;
    }

    public OPAAccessDeniedException(String message, Throwable cause, OPAResponse opaResponse) {
        super(message, cause);
        this.opaResponse = opaResponse;
    }
}
