package de.schmiereck.liquidVote.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private jakarta.persistence.EntityManager entityManager;

    @Test
    void createUpdateDeleteUser() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Test");
        userEntity.setLastName("User");
        userEntity.setUsername("testUser");
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("secret");
        userEntity.setRoles("REGISTERED");

        final UserEntity createdUserEntity = this.userService.createUser(userEntity);
        assertThat(createdUserEntity.getId()).isNotNull();

        this.entityManager.flush();
        this.entityManager.clear();

        final UserEntity updatedUserEntity = this.userService.updateUser(createdUserEntity.getId(), user -> {
            user.setLastName("Updated");
            user.setEmail("user@example.com");
        });

        assertThat(updatedUserEntity.getLastName()).isEqualTo("Updated");
        assertThat(updatedUserEntity.getEmail()).isEqualTo("user@example.com");

        this.userService.deleteUser(updatedUserEntity.getId());
        this.entityManager.flush();
        this.entityManager.clear();

        Optional<UserEntity> deleted = this.userRepository.findById(updatedUserEntity.getId());
        assertThat(deleted).isEmpty();
    }
}
