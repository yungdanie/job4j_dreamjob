package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository @ThreadSafe
public class PostStore {
    private static final AtomicInteger ATOMIC = new AtomicInteger(0);
    private final Map<Integer, Post> store = new ConcurrentHashMap<>();

    public Collection<Post> findAll() {
        return store.values();
    }

    public void add(Post post) {
        post.setId(ATOMIC.incrementAndGet());
        post.setCreated(LocalDateTime.now());
        store.put(post.getId(), post);
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