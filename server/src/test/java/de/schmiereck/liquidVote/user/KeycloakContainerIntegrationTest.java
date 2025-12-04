package de.schmiereck.liquidVote.user;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import dasniko.testcontainers.keycloak.KeycloakContainer;

@SpringBootTest
@Testcontainers
@Disabled("Deaktiviert, da eine funktionierende Docker-Umgebung für Testcontainers erforderlich ist.")
class KeycloakContainerIntegrationTest {

    @Container
    private static final KeycloakContainer keycloakContainer = new KeycloakContainer("quay.io/keycloak/keycloak:24.0.4")
            .withRealmImportFile("keycloak/liquidvote-realm.json");

    @DynamicPropertySource
    static void registerKeycloakProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.security.oauth2.resourceserver.jwt.issuer-uri", () ->
                keycloakContainer.getAuthServerUrl() + "/realms/liquidvote");
    }

    @Test
    void contextLoads() {
        // Test that the Spring context loads successfully with the Testcontainers configuration
    }
}
