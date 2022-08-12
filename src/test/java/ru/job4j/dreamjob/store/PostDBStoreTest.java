package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.List;

class PostDBStoreTest {

    private static final BasicDataSource pool = new Main().loadPool();
    private static final PostDBStore store = new PostDBStore(pool);

    @BeforeEach
    public void reset() {
        store.reset();
    }

    @Test
    public void whenFindPostById() {
        Post post = store.add(new Post(0, "Java Job", new City(1)));
        Post postInDb = store.findById(post.getId());
        Assertions.assertThat(postInDb.getId()).isEqualTo(post.getId());
    }

    @Test
    public void whenCreatePost() {
        Post post = store.add(new Post(0, "Java Job", new City(1)));
        Post postInDb = store.findById(post.getId());
        Assertions.assertThat(post).isEqualTo(postInDb);
    }

    @Test
    public void whenUpdatePost() {
        Post post = store.add(new Post(0, "Java Job", new City(1)));
        post.setName("New name");
        post.setDescription("New Description");
        post.setCity(new City(2));
        store.update(post);
        Assertions.assertThat(store.findById(post.getId())).isEqualTo(post);
    }

    @Test
    public void whenFindAllPost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post1 = store.add(new Post(1, "Java Job", new City(1)));
        Post post2 = store.add(new Post(2, "Java Job", new City(2)));
        Post post3 = store.add(new Post(3, "Java Job", new City(3)));
        Collection<Post> postCollection = List.of(post1, post2, post3);
        Assertions.assertThat(store.findAll()).isEqualTo(postCollection);
    }
}