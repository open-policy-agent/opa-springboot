package io.github.open_policy_agent.opa.springboot.authorization;

import io.github.open_policy_agent.opa.springboot.OPAResponse;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authorization.AuthorizationDecision;

/**
 * Extends {@link AuthorizationDecision} which conveys {@link OPAResponse}.
 */
@Getter
public class OPAAuthorizationDecision extends AuthorizationDecision {
    @Nullable
    private final OPAResponse opaResponse;

    public OPAAuthorizationDecision(boolean granted, @Nullable OPAResponse opaResponse) {
        super(granted);
        this.opaResponse = opaResponse;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [granted=" + isGranted() + ", opaResponse=" + opaResponse + "]";
    }
}
