package de.schmiereck.liquidVote.security;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.keycloak.platform.Platform;
import org.keycloak.platform.PlatformProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.nio.file.Paths;

@Component
@ConditionalOnProperty(value = "embedded.keycloak.enabled", havingValue = "true", matchIfMissing = true)
public class EmbeddedKeycloakServer {

    private final ResourceLoader resourceLoader;
    private final String importPath;
    private final String configFile;
    private final String httpPort;
    private final String hostname;
    private final boolean devMode;
    private final String projectDir;

    public EmbeddedKeycloakServer(ResourceLoader resourceLoader,
                                  @Value("${embedded.keycloak.import-path:classpath:keycloak/liquidvote-realm.json}") String importPath,
                                  @Value("${embedded.keycloak.config:classpath:keycloak/embedded-keycloak.conf}") String configFile,
                                  @Value("${embedded.keycloak.port:8180}") String httpPort,
                                  @Value("${embedded.keycloak.host:0.0.0.0}") String hostname,
                                  @Value("${embedded.keycloak.dev-mode:true}") boolean devMode,
                                  @Value("${embedded.keycloak.project-dir:#{null}}") String projectDir) {
        this.resourceLoader = resourceLoader;
        this.importPath = importPath;
        this.configFile = configFile;
        this.httpPort = httpPort;
        this.hostname = hostname;
        this.devMode = devMode;
        this.projectDir = projectDir;
    }

    private EmbeddedKeycloakApplication runner;

    @PostConstruct
    public void startKeycloak() throws Exception {
        PlatformProvider platform = Platform.getPlatform();
        if (platform != null) {
            platform.onStartup(() -> {});
        }

        String resolvedImport = resolvedFile(importPath);
        String resolvedConfig = resolvedFile(configFile);

        runner = new EmbeddedKeycloakApplication(hostname,
            Integer.parseInt(httpPort),
            resolvedConfig,
            resolvedImport,
            devMode,
            //"C:\\Users\\SCMJ178\\IdeaProjects\\liquidVote\\server\\target\\quarkus");
            resolveProjectDir());
        runner.start();
    }

    private String resolvedFile(String location) throws IOException {
        Resource resource = resourceLoader.getResource(location);
        if (!resource.exists()) {
            return location;
        }
        Path tempFile = Files.createTempFile("keycloak-" + UUID.randomUUID(), resource.getFilename());
        try (InputStream stream = resource.getInputStream()) {
            Files.copy(stream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }
        return tempFile.toAbsolutePath().toString();
    }

    private String resolveProjectDir() {
        if (projectDir != null && !projectDir.isBlank()) {
            return projectDir;
        }
        String envProjectDir = System.getenv("KEYCLOAK_PROJECT_DIR");
        if (envProjectDir != null && !envProjectDir.isBlank()) {
            return envProjectDir;
        }
        Path moduleRoot = locateServerModuleRoot();
        Path unpackedDir = moduleRoot.resolve("target").resolve("embedded-keycloak").resolve("keycloak-" + org.keycloak.common.Version.VERSION);
        if (Files.exists(unpackedDir)) {
            return unpackedDir.toString();
        }
        return moduleRoot.toString();
    }

    private Path locateServerModuleRoot() {
        Path cwd = Paths.get("").toAbsolutePath();
        if (Files.exists(cwd.resolve("pom.xml")) && "server".equals(cwd.getFileName().toString())) {
            return cwd;
        }
        Path moduleDir = cwd.resolve("server");
        if (Files.exists(moduleDir.resolve("pom.xml"))) {
            return moduleDir;
        }
        return cwd;
    }

    @PreDestroy
    public void stop() {
        Platform.getPlatform().onShutdown(() -> {});
        if (runner != null) {
            runner.stop();
            runner = null;
        }
    }
}
