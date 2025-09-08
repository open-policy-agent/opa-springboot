package org.openpolicyagent.opa.springboot.authorization;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.openpolicyagent.opa.springboot.authorization.BaseAuthorizationEventListenerTest;
import org.openpolicyagent.opa.springboot.authorization.OPAAuthorizationDecision;
import org.springframework.security.test.context.support.WithMockUser;

public class DefaultCustomAuthorizationEventListenerTest extends BaseAuthorizationEventListenerTest {

    @WithMockUser(username = "denied_user")
    @Test
    public void testDefaultAuthorizationDeniedEvent() throws Exception {
        getMockMvc().perform(get("/test/hello"))
                .andExpect(status().isForbidden());

        assertThat(getAuthorizationEventListener().getLastAuthorizationDeniedEvent()).isNotNull();
        assertThat(getAuthorizationEventListener().getLastAuthorizationDeniedEvent().getAuthorizationDecision())
                .isInstanceOf(OPAAuthorizationDecision.class);
        var opaResponse = ((OPAAuthorizationDecision) getAuthorizationEventListener().getLastAuthorizationDeniedEvent()
                .getAuthorizationDecision()).getOpaResponse();
        assertThat(opaResponse.getDecision()).isEqualTo(false);
    }

    @WithMockUser(username = "granted_user")
    @Test
    public void testDefaultAuthorizationGrantedEvent() throws Exception {
        getMockMvc().perform(get("/test/hello"))
                .andExpect(status().isOk());

        assertThat(getAuthorizationEventListener().getLastAuthorizationGrantedEvent()).isNull();
    }
}
