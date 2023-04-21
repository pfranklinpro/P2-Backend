package franklin.paul.sylvester.services;

import franklin.paul.sylvester.dtos.requests.NewReplyRequest;
import franklin.paul.sylvester.entities.Post;
import franklin.paul.sylvester.entities.Reply;
import franklin.paul.sylvester.entities.User;
import franklin.paul.sylvester.repositories.ReplyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ReplyServiceTest {
    private ReplyService sut;
    private final ReplyRepository mockReplyRepo = Mockito.mock(ReplyRepository.class);

    @Before
    public void init () {
        sut = new ReplyService(mockReplyRepo);
    }

    @Test
    public void test_saveReplyByUserId_givenValidUserId() {
        // Arrange
        ReplyService spySut = Mockito.spy(sut);
        NewReplyRequest stubbedReq = new NewReplyRequest("sample reply", UUID.randomUUID().toString(),
                null);

        String validUserId = UUID.randomUUID().toString();
        String username = "testUsername";
        String displayName = "Test Display Name";

        // Act
        spySut.saveReplyByUserId(stubbedReq, validUserId, username, displayName);

        // Assert
        Mockito.verify(mockReplyRepo, Mockito.times(1)).save(Mockito.anyString(),
                Mockito.eq(stubbedReq.getReply()), Mockito.any(Date.class), Mockito.eq(stubbedReq.getImgUrl()),
                Mockito.eq(validUserId), Mockito.eq(stubbedReq.getPostId()), Mockito.eq(username),
                Mockito.eq(displayName));
    }

    @Test
    public void test_getAllRepliesByPostId_givenValidPostId() {
        // Arrange
        String validPostId = UUID.randomUUID().toString();
        List<Reply> stubbedReplies = new ArrayList<>();
        User user = new User(UUID.randomUUID().toString(), "testUsername", "mRMEY476",
                "testUsername@testUsername.com", new Date(2022,12,13), true,
                null);

        Post post = new Post(validPostId, new Date(2022,12,13), "first post",
                null, user, "testUsername", "Test Display Name");

        Reply reply = new Reply(UUID.randomUUID().toString(), "sample reply", new Date(), null, user,
                post, "testUsername", "Test Display Name");

        stubbedReplies.add(reply);

        Mockito.when(mockReplyRepo.findAllByPostId(validPostId)).thenReturn(stubbedReplies);

        // Act
        List<Reply> condition = sut.getAllRepliesByPostId(validPostId);

        // Assert
        assertFalse(condition.isEmpty());
        assertEquals(validPostId, condition.get(0).getPost().getPostId());
    }
}