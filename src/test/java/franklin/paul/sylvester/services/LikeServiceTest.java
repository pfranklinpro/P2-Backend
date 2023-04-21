package franklin.paul.sylvester.services;


import franklin.paul.sylvester.entities.Like;
import franklin.paul.sylvester.entities.Post;
import franklin.paul.sylvester.entities.User;
import franklin.paul.sylvester.repositories.LikeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class LikeServiceTest {
    private LikeService sut;
    private final LikeRepository mockLikeRepo = Mockito.mock(LikeRepository.class);

    @Before
    public void init() {
        sut = new LikeService(mockLikeRepo);
    }

    @Test
    public void test_saveLikeByUserIdAndPostId_givenValidUserIdAndPostId() {
        // Arrange
        LikeService spySut = Mockito.spy(sut);
        String validUserId = UUID.randomUUID().toString();
        String validPostId = UUID.randomUUID().toString();
        String username = "testUsername";
        String displayName = "Test Display Name";

        // Act
        spySut.saveLikeByUserIdAndPostId(validUserId, validPostId, username, displayName);

        // Assert
        Mockito.verify(mockLikeRepo, Mockito.times(1)).save(Mockito.anyString(),
                Mockito.eq(validUserId), Mockito.eq(validPostId), Mockito.eq(username), Mockito.eq(displayName));
    }

    @Test
    public void test_deleteLikeByUserIdAndPostId_givenValidUserIdAndPostId() {
        // Arrange
        LikeService spySut = Mockito.spy(sut);
        String validUserId = UUID.randomUUID().toString();
        String validPostId = UUID.randomUUID().toString();

        // Act
        spySut.deleteLikeByUserIdAndPostId(validUserId, validPostId);

        // Assert
        Mockito.verify(mockLikeRepo, Mockito.times(1)).delete(Mockito.eq(validUserId),
                Mockito.eq(validPostId));
    }

    @Test
    public void test_getAllLikesByUserId_givenValidUserId() {
        // Arrange
        String validUserId = UUID.randomUUID().toString();
        List<Like> stubbedLikes = new ArrayList<>();
        User user = new User(validUserId, "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        Like like = new Like(UUID.randomUUID().toString(), user, null, "testUsername",
                "Test Display Name");

        stubbedLikes.add(like);

        Mockito.when(mockLikeRepo.findAllByUserId(validUserId)).thenReturn(stubbedLikes);

        // Act
        List<Like> condition = sut.getAllLikesByUserId(validUserId);

        // Assert
        assertFalse(condition.isEmpty());
        assertEquals(validUserId, condition.get(0).getUser().getUserId());
    }

    @Test
    public void test_getAllLikesByPostId_givenValidPostId() {
        // Arrange
        String validPostId = UUID.randomUUID().toString();
        List<Like> stubbedLikes = new ArrayList<>();
        Post post = new Post(validPostId, new Date(2022,12,13), "first post", null,
                null, null);

        Like like = new Like(UUID.randomUUID().toString(), null, post, null, null);
        stubbedLikes.add(like);

        Mockito.when(mockLikeRepo.findAllByUserId(validPostId)).thenReturn(stubbedLikes);

        // Act
        List<Like> condition = sut.getAllLikesByUserId(validPostId);

        // Assert
        assertFalse(condition.isEmpty());
        assertEquals(validPostId, condition.get(0).getPost().getPostId());
    }

    @Test
    public void test_getAllLikedPostIdsByUserId_givenValidUserId() {
        // Arrange
        String validUserId = UUID.randomUUID().toString();
        List<String> stubbedLikedPostIds = new ArrayList<>();
        String likedPostId = UUID.randomUUID().toString();
        stubbedLikedPostIds.add(likedPostId);

        Mockito.when(mockLikeRepo.findAllLikedPostIdsByUserId(validUserId)).thenReturn(stubbedLikedPostIds);

        // Act
        List<String> condition = sut.getAllLikedPostIdsByUserId(validUserId);

        // Assert
        assertFalse(condition.isEmpty());
    }

    @Test
    public void test_getLikeCountByPostId_givenValidPostId() {
        // Arrange
        String validPostId = UUID.randomUUID().toString();

        Mockito.when(mockLikeRepo.countAllLikesByPostId(validPostId)).thenReturn(3);

        // Act
        int condition = sut.getLikeCountByPostId(validPostId);

        // Assert
        assertEquals(3, condition);
    }

    @Test
    public void test_isLiked_givenValidUserIdAndPostId() {
        // Arrange
        String validUserId = UUID.randomUUID().toString();
        String validPostId = UUID.randomUUID().toString();

        User user = new User(validUserId, "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        Post post = new Post(validPostId, new Date(2022,12,13), "first post", null,
                null, null);

        Like stubbedLike = new Like(UUID.randomUUID().toString(), user, post,
                "testUsername", "Test Display Name");

        Mockito.when(mockLikeRepo.findByUserIdAndPostId(validUserId, validPostId)).thenReturn(stubbedLike);

        // Act
        boolean condition = sut.isLiked(validUserId, validPostId);

        // Assert
        assertTrue(condition);
    }
}