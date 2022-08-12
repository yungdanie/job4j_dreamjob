package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostDBStore;
import java.util.Collection;

@Service @ThreadSafe
public class PostService {
    private final PostDBStore store;
    private final CityService cityService;

    public PostService(PostDBStore postStore, CityService cityService) {
        this.store = postStore;
        this.cityService = cityService;
    }

    public Collection<Post> findAll() {
        Collection<Post> posts = store.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return posts;
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
