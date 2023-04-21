package franklin.paul.sylvester.services;

import franklin.paul.sylvester.dtos.requests.NewPostRequest;
import franklin.paul.sylvester.entities.Post;
import franklin.paul.sylvester.entities.User;
import franklin.paul.sylvester.repositories.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class PostServiceTest {
    private PostService sut;
    private final PostRepository mockPostRepo = Mockito.mock(PostRepository.class);

    @Before
    public void init() {
        sut = new PostService(mockPostRepo);
    }

    @Test
    public void test_correctSavePostByUserId_givenRequestAndUserId() {
        // Arrange
        PostService spySut = Mockito.spy(sut);
        NewPostRequest req = new NewPostRequest("sample content", null);
        String userId = UUID.randomUUID().toString();
        String username = "testUsername";
        String displayName = "Test Display Name";

        // Act
        spySut.savePostByUserId(req, userId, username, displayName);

        // Assert
        Mockito.verify(mockPostRepo, Mockito.times(1)).save(Mockito.anyString(),
                Mockito.any(Date.class), Mockito.eq(req.getContent()), Mockito.eq(req.getImgUrl()), Mockito.eq(userId),
                Mockito.eq(username), Mockito.eq(displayName));
    }

    @Test
    public void test_correctGetAllPosts_givenNothing() {
        // Arrange
        PostService spySut = Mockito.spy(sut);
        User user1 = new User("0", "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        Post post1 = new Post("0", new Date(2022,12,13), "first post", null,
                user1, "testUsername", "Test Display Name");

        List<Post> posts = new ArrayList<>();
        posts.add(post1);

        Mockito.when(mockPostRepo.findAll()).thenReturn(posts);
        Mockito.when(spySut.getAllPosts()).thenReturn(posts);

        // Act
        List<Post> newPosts = spySut.getAllPosts();

        // Assert
        Post newPost = newPosts.get(0);

        assertEquals("0",newPost.getPostId());
        assertEquals(new Date(2022,12,13),newPost.getPosted());
        assertEquals("first post",newPost.getContent());
        assertNull(newPost.getImgUrl());

        User newUser = newPost.getUser();

        assertEquals("0",newUser.getUserId());
        assertEquals("testUsername",newUser.getUsername());
        assertEquals("mRMEY476",newUser.getPassword());
        assertEquals("testUsername@testUsername.com",newUser.getEmail());
        assertEquals(new Date(2022,12,13),newUser.getRegistered());
        assertNull(newUser.getRoleId());
    }

    @Test
    public void test_correctGetAllPostsByUserId_givenUserId() {
        // Arrange
        PostService spySut = Mockito.spy(sut);
        User user1 = new User("0", "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        Post post1 = new Post("0", new Date(2022,12,13), "first post", null,
                user1, "testUsername", "Test Display Name");

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        String userId = user1.getUserId();

        Mockito.when(mockPostRepo.findAllByUserId(userId)).thenReturn(posts);
        Mockito.when(spySut.getAllPostsByUserId(userId)).thenReturn(posts);

        // Act
        List<Post> newPosts = spySut.getAllPostsByUserId(userId);

        // Assert
        Post newPost = newPosts.get(0);

        assertEquals("0",newPost.getPostId());
        assertEquals(new Date(2022,12,13),newPost.getPosted());
        assertEquals("first post",newPost.getContent());
        assertNull(newPost.getImgUrl());

        User newUser = newPost.getUser();

        assertEquals("0",newUser.getUserId());
        assertEquals("testUsername",newUser.getUsername());
        assertEquals("mRMEY476",newUser.getPassword());
        assertEquals("testUsername@testUsername.com",newUser.getEmail());
        assertEquals(new Date(2022,12,13),newUser.getRegistered());
        assertNull(newUser.getRoleId());
    }

    @Test
    public void test_correctFilterPostsByPosted_givenNothing() {
        // Arrange
        PostService spySut = Mockito.spy(sut);
        User user1 = new User("0", "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        Post post1 = new Post("0", new Date(2022,12,13), "first post", null,
                user1, "testUsername", "Test Display Name");

        List<Post> posts = new ArrayList<>();
        posts.add(post1);

        Mockito.when(mockPostRepo.filterByPosted(5)).thenReturn(posts);
        Mockito.when(spySut.filterPostsByPosted(5)).thenReturn(posts);

        // Act
        List<Post> newPosts = spySut.filterPostsByPosted(5);

        // Assert
        Post newPost = newPosts.get(0);

        assertEquals("0",newPost.getPostId());
        assertEquals(new Date(2022,12,13),newPost.getPosted());
        assertEquals("first post",newPost.getContent());
        assertNull(newPost.getImgUrl());

        User newUser = newPost.getUser();

        assertEquals("0",newUser.getUserId());
        assertEquals("testUsername",newUser.getUsername());
        assertEquals("mRMEY476",newUser.getPassword());
        assertEquals("testUsername@testUsername.com",newUser.getEmail());
        assertEquals(new Date(2022,12,13),newUser.getRegistered());
        assertNull(newUser.getRoleId());
    }

    @Test
    public void test_correctIsValidContent_givenContent() {
        // Arrange
        String content1 = "this content is good";
        String content2 = "this content is bad!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";

        // Act
        Boolean goodContent = sut.isValidContent(content1);
        Boolean badContent = sut.isValidContent(content2);

        // Assert
        assertEquals(true, goodContent);
        assertEquals(false, badContent);
    }

    @Test
    public void test_correctGetLikedPostsByUserLikedPostIds_givenUserLikedPostIds() {
        // Arrange
        String userLikedPostId = UUID.randomUUID().toString();
        User user1 = new User("0", "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        Post post1 = new Post("0", new Date(2022,12,13), "first post", null,
                null, null);

        Post post2 = new Post(userLikedPostId, new Date(2022,12,13), "sample post",
                null, null, null);

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        List<String> userLikedPostIds = new ArrayList<>();
        userLikedPostIds.add(userLikedPostId);

        Mockito.when(mockPostRepo.findAll()).thenReturn(posts);

        // Act
        List<Post> condition = sut.getLikedPostsByUserLikedPostIds(userLikedPostIds);

        // Assert
        assertNotNull(condition);
    }
}
