package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.Collection;

@Service @ThreadSafe
public class PostService {
    private final PostStore store;

    public PostService(PostStore postStore) {
        this.store = postStore;
    }

    public Collection<Post> findAll() {
        return store.findAll();
    }

    public void update(Post post) {
        store.update(post);
    }

    public void add(Post post) {
        store.add(post);
    }

    public Post findById(int id) {
        return store.findById(id);
    }
}
