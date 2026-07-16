package com.example.demo.service;

import com.example.demo.exception.DuplicateEmailException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) {
        user.setId(null);
        user.setName(user.getName().trim());
        user.setEmail(normalizeEmail(user.getEmail()));
        ensureEmailAvailable(user.getEmail(), null);
        return repository.save(user);
    }

    @Transactional
    public User update(Long id, User changes) {
        User user = findById(id);
        String email = normalizeEmail(changes.getEmail());
        ensureEmailAvailable(email, id);

        user.setName(changes.getName().trim());
        user.setEmail(email);
        return repository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        repository.delete(user);
    }

    private void ensureEmailAvailable(String email, Long currentUserId) {
        boolean exists = currentUserId == null
                ? repository.existsByEmailIgnoreCase(email)
                : repository.existsByEmailIgnoreCaseAndIdNot(email, currentUserId);

        if (exists) {
            throw new DuplicateEmailException(email);
        }
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
