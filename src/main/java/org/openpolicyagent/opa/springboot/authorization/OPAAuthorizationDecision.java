package org.openpolicyagent.opa.springboot.authorization;

import org.openpolicyagent.opa.springboot.OPAResponse;
import org.springframework.security.authorization.AuthorizationDecision;

import lombok.Getter;

/**
 * Extends {@link AuthorizationDecision} which conveys {@link OPAResponse}.
 */
@Getter
public class OPAAuthorizationDecision extends AuthorizationDecision {
    private final OPAResponse opaResponse;

    public OPAAuthorizationDecision(boolean granted, OPAResponse opaResponse) {
        super(granted);
        this.opaResponse = opaResponse;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [granted=" + isGranted() + ", opaResponse=" + opaResponse + "]";
    }
}
