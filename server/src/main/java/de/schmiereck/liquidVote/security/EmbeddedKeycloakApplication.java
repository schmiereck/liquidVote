package de.schmiereck.liquidVote.security;

import io.quarkus.runtime.Quarkus;
import org.keycloak.quarkus.runtime.KeycloakMain;
import org.keycloak.quarkus.runtime.cli.ExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class EmbeddedKeycloakApplication {

    private static final Logger log = LoggerFactory.getLogger(EmbeddedKeycloakApplication.class);

    private final String host;
    private final int port;
    private final String configFile;
    private final String realmImportFile;
    private final boolean devMode;
    private final String projectDir;
    private KeycloakMain keycloakMain;

    EmbeddedKeycloakApplication(String host,
                                int port,
                                String configFile,
                                String realmImportFile,
                                boolean devMode,
                                String projectDir) {
        this.host = host;
        this.port = port;
        this.configFile = configFile;
        this.realmImportFile = realmImportFile;
        this.devMode = devMode;
        this.projectDir = projectDir;
    }

    void start() {
        if (keycloakMain != null) {
            log.debug("Embedded Keycloak already running");
            return;
        }

        // Setze die kc.home.dir Property. Diese ist für die Keycloak-Runtime relevant.
        System.setProperty("kc.home.dir", projectDir);

        List<String> args = new ArrayList<>();
        args.add(devMode ? "start-dev" : "start");
        args.add("--http-port=" + port);
        args.add("--hostname=" + host);
        args.add("--config-file=" + configFile);
        args.add("--import-realm");
        args.add("--import-path=" + realmImportFile);
        //args.add("--project-dir=" + projectDir);

        log.info("Bootstrapping embedded Keycloak (mode={}, host={}, port={}, projectDir={})",
            devMode ? "dev" : "prod", host, port, projectDir);

        keycloakMain = new KeycloakMain();

        // Wichtig: KeycloakMain ist ein Wrapper. Die tatsächliche Keycloak/Quarkus-Logik
        // wird die kc.home.dir System Property verwenden, um das Basisverzeichnis zu finden.
        keycloakMain.start(new ExecutionExceptionHandler(), new PrintWriter(System.out, true), args.toArray(String[]::new));
    }

    void stop() {
        if (keycloakMain != null) {
            try {
                Quarkus.asyncExit();
            } finally {
                keycloakMain = null;
            }
        }
    }
}
