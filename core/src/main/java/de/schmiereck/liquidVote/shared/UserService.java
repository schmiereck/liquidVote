package de.schmiereck.liquidVote.shared;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(final UserEntity entity) {
        return this.userRepository.save(entity);
    }

    public UserEntity updateUser(final Long id, final Consumer<UserEntity> updater) {
        final UserEntity entity = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
        updater.accept(entity);
        return this.userRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<UserEntity> listUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUser(final Long userEntityId) {
        this.userRepository.deleteById(userEntityId);
    }
}
