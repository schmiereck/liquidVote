package de.schmiereck.liquidVote.security;

import io.quarkus.runtime.ApplicationLifecycleManager;
import org.keycloak.quarkus.runtime.KeycloakMain;
import org.keycloak.quarkus.runtime.cli.ExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class EmbeddedKeycloakApplication {

    private static final Logger log = LoggerFactory.getLogger(EmbeddedKeycloakApplication.class);

    private final String host;
    private final int port;
    private final String configFile;
    private final String realmImportFile;
    private final boolean devMode;
    private KeycloakMain keycloakMain;
    private ExecutionExceptionHandler exceptionHandler;

    EmbeddedKeycloakApplication(String host,
                                int port,
                                String configFile,
                                String realmImportFile,
                                boolean devMode) {
        this.host = host;
        this.port = port;
        this.configFile = configFile;
        this.realmImportFile = realmImportFile;
        this.devMode = devMode;
    }

    void start() {
        if (keycloakMain != null) {
            log.debug("Embedded Keycloak already running");
            return;
        }

        List<String> args = new ArrayList<>();
        args.add(devMode ? "start-dev" : "start");
        args.add("--http-port=" + port);
        args.add("--hostname=" + host);
        args.add("--config-file=" + configFile);
        args.add("--import-realm");
        args.add("--import-path=" + realmImportFile);

        log.info("Bootstrapping embedded Keycloak (mode={}, host={}, port={})", devMode ? "dev" : "prod", host, port);
        keycloakMain = new KeycloakMain();
        exceptionHandler = new ExecutionExceptionHandler();
        keycloakMain.start(exceptionHandler, new java.io.PrintWriter(System.out, true), args.toArray(String[]::new));
    }

    void stop() {
        if (keycloakMain != null) {
            try {
                //KeycloakMain.DeploymentInfoHolder holder = keycloakMain.getCurrent();
                //if (holder != null) {
                //    holder.getShutdownHook().run();
                //}
                ApplicationLifecycleManager.exit();
            } finally {
                keycloakMain = null;
                exceptionHandler = null;
            }
        }
    }
}
