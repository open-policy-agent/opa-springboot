package io.github.open_policy_agent.opa.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

/**
 * This class models the data to be returned from an OPA Spring Boot SDK policy.
 * The
 * <a href=
 * "https://github.com/open-policy-agent/opa-springboot/tree/main/docs/reference/input-output-schema#output">response
 * schema</a> is
 * compliant with the <a href="https://openid.github.io/authzen">AuthZEN
 * spec</a>.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OPAResponse {

    private boolean decision;
    @Nullable
    private OPAResponseContext context;

    public boolean getDecision() {
        return decision;
    }

    /**
     * Wraps {@link OPAResponseContext#getReasonForDecision(String)}. If the context
     * is omitted (which the spec
     * permits), then it returns null.
     */
    public @Nullable String getReasonForDecision(String searchKey) {
        if (context == null) {
            return null;
        }
        return context.getReasonForDecision(searchKey);
    }
}
