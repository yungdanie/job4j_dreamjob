package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.Collection;

public class PostService {
    private final PostStore store = PostStore.instOf();

    private static final PostService postService = new PostService();

    private PostService() {
    }

    public static PostService instOf() {
        return postService;
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
