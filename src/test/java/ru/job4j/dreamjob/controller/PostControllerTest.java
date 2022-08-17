package ru.job4j.dreamjob.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;

class PostControllerTest {

    @Test
    public void whenPosts() {
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        HttpSession httpSession = mock(HttpSession.class);
        Model model = mock(Model.class);
        Post post1 = new Post(1, "Post 1");
        Post post2 = new Post(2, "Post 2");
        Collection<Post> posts = List.of(post1, post2);
        when(postService.findAll()).thenReturn(posts);
        PostController postController = new PostController(postService, cityService);
        String page = postController.posts(model, httpSession);
        verify(model).addAttribute("posts", posts);
        Assertions.assertThat(page).isEqualTo("posts");
    }

    @Test
    public void whenFormAddPost() {
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        HttpSession httpSession = mock(HttpSession.class);
        Model model = mock(Model.class);
        PostController postController = new PostController(postService, cityService);
        String page = postController.formAddPost(model, httpSession);
        verify(cityService).getAllCities();
        Assertions.assertThat(page).isEqualTo("addPost");
    }

    @Test
    public void whenCreatePost() {
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        Post post = new Post(1, "Post 1");
        PostController postController = new PostController(postService, cityService);
        String page = postController.createPost(post);
        verify(postService).add(post);
        Assertions.assertThat(page).isEqualTo("redirect:/posts");
    }

    @Test
    public void whenFormUpdatePost() {
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        HttpSession httpSession = mock(HttpSession.class);
        Model model = mock(Model.class);
        int id = 1;
        Post post = new Post(id, "Post 1");
        when(postService.findById(id)).thenReturn(post);
        PostController postController = new PostController(postService, cityService);
        String page = postController.formUpdatePost(model, id, httpSession);
        verify(postService).findById(id);
        verify(model).addAttribute("post", post);
        verify(cityService).getAllCities();
        Assertions.assertThat(page).isEqualTo("updatePost");
    }

    @Test
    public void whenUpdatePost() {
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        Post post = new Post(1, "Old Post");
        PostController postController = new PostController(postService, cityService);
        String page = postController.updatePost(post);
        verify(postService).update(post);
        Assertions.assertThat(page).isEqualTo("redirect:/posts");
    }
}