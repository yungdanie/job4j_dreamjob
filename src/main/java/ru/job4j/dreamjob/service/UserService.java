package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDbStore;

import java.util.Collection;
import java.util.Optional;

@Service
@ThreadSafe
public class UserService {

    private final UserDbStore store;

    public UserService(UserDbStore userDbStore) {
        this.store = userDbStore;
    }

    public Optional<User> add(User user) {
        return store.add(user);
    }

    public Collection<User> findAll() {
        return store.findAll();
    }

    public User findById(int id) {
        return store.findById(id);
    }

    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        return store.findByEmailAndPwd(email, password);
    }
}
