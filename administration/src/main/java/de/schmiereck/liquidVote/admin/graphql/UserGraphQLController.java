package de.schmiereck.liquidVote.admin.graphql;

import de.schmiereck.liquidVote.shared.UserEntity;
import de.schmiereck.liquidVote.shared.UserRole;
import de.schmiereck.liquidVote.shared.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserGraphQLController {

    private final UserService userService;

    public UserGraphQLController(final UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('ROLE_userAdmin')")
    public List<UserEntity> users() {
        return this.userService.listUsers();
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('ROLE_userAdmin')")
    public UserEntity user(@Argument final Long userEntityId) {
        return this.userService.readUserByUserEntityId(userEntityId);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ROLE_userAdmin')")
    public UserEntity createUser(@Argument UserInput input) {
        final UserEntity entity = new UserEntity();
        applyInput(entity, input);
        return this.userService.createUser(entity);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ROLE_userAdmin')")
    public UserEntity updateUser(@Argument final Long userEntityId, @Argument final UserInput input) {
        return this.userService.updateUser(userEntityId, user -> applyInput(user, input));
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ROLE_userAdmin')")
    public boolean deleteUser(@Argument final Long userEntityId) {
        this.userService.deleteUser(userEntityId);
        return true;
    }

    private void applyInput(UserEntity entity, UserInput input) {
        entity.setFirstName(input.firstName());
        entity.setLastName(input.lastName());
        entity.setUsername(input.username());
        entity.setEmail(input.email());
        if (input.password() != null) {
            entity.setPassword(input.password());
        }
        entity.setRoles(input.roles().stream().map(UserRole::valueOf).toList());
    }

    public record UserInput(
            String firstName,
            String lastName,
            String username,
            String email,
            String password,
            List<String> roles
    ) {
    }
}
