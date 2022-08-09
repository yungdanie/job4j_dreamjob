package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStore {

    private static final AtomicInteger ATOMIC = new AtomicInteger(0);
    private final Map<Integer, Post> store = new ConcurrentHashMap<>();

    public Collection<Post> findAll() {
        return store.values();
    }

    public void add(Post post) {
        int curr;
        int incrCurr;
        do {
            curr = ATOMIC.get();
            incrCurr = curr + 1;
        } while (!ATOMIC.compareAndSet(curr, incrCurr));
        post.setId(incrCurr);
        post.setCreated(LocalDateTime.now());
        store.putIfAbsent(incrCurr, post);
    }

    public Post findById(int id) {
        return store.get(id);
    }

    public void update(Post post) {
        Post old = store.get(post.getId());
        old.setName(post.getName());
        old.setDescription(post.getDescription());
    }
}