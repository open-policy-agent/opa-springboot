package org.openpolicyagent.opa.springboot.input;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.openpolicyagent.opa.OPAClient;
import org.openpolicyagent.opa.springboot.BaseIntegrationTest;
import org.openpolicyagent.opa.springboot.OPAAuthorizationManager;
import org.openpolicyagent.opa.springboot.OPAPathSelector;
import org.openpolicyagent.opa.springboot.autoconfigure.OPAProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Import(BaseOpaInputCustomizerIntegrationTest.CustomOPAInputCustomizerConfig.class)
public class BaseOpaInputCustomizerIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private OPAAuthorizationManager opaAuthorizationManager;

    protected Map<String, Object> callAuthorizationManagerAndVerify() {
        var expectedResponseContextData = createNullMockAuthOPAInput();
        var mockAuth = createNullMockAuthentication();
        when(authenticationSupplier.get()).thenReturn(mockAuth);

        var actualResponse = getOpaAuthorizationManager().opaRequest(authenticationSupplier, context);

        assertNotNull(actualResponse.getContext());
        assertNotNull(actualResponse.getContext().getData());
        var actualResponseContextData = actualResponse.getContext().getData();
        assertNotEquals(expectedResponseContextData, actualResponseContextData);
        return actualResponseContextData;
    }

    protected OPAAuthorizationManager getOpaAuthorizationManager() {
        return opaAuthorizationManager;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @TestConfiguration
    public static class CustomOPAInputCustomizerConfig {
        @Bean
        public OPAClient opaClient(OPAProperties opaProperties) {
            return new OPAClient(opaProperties.getUrl(), HEADERS);
        }

        @Bean
        public OPAPathSelector opaPathSelector() {
            return (authentication, requestAuthorizationContext, opaInput) -> "policy/echo";
        }
    }
}
