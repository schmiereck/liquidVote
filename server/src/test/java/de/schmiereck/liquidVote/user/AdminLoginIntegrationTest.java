package de.schmiereck.liquidVote.user;

import de.schmiereck.liquidVote.shared.UserEntity;
import de.schmiereck.liquidVote.shared.UserRepository;
import de.schmiereck.liquidVote.shared.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class AdminLoginIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void adminUserIsSeededAndPasswordMatches() {
        final UserEntity admin = this.userRepository.findByUsername("admin")
                .orElseThrow(() -> new IllegalStateException("Seeded admin user not found"));

        assertThat(admin.getEmail()).isEqualTo("admin@example.com");
        assertThat(admin.getRoles()).contains(UserRole.USER_ADMIN);
        System.out.println("Seeded admin password hash: " + admin.getPassword());
        assertThat(this.passwordEncoder.matches("Admin123!", admin.getPassword())).isTrue();
    }
}
