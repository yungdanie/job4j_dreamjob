package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {
    private static final PostStore INST = new PostStore();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "vk.com", "сайт для общения", new Date(1659618258000L)));
        posts.put(2, new Post(2, "odnoklasniky.com", "сайт для пожилых людей", new Date(1659618258000L)));
        posts.put(3, new Post(3, "google.com", "поисковик", new Date(1659618258000L)));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}